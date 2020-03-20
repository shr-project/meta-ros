#!/bin/bash

SCRIPT_NAME="layerpin.sh"
SCRIPT_VERSION="1.0.0"

if [ $# -lt 4 ]; then
  echo $SCRIPT_NAME $SCRIPT_VERSION
  echo ""
  echo "Usage $0 layer_root U|C|T|P [tag_name] layer_list"
  echo "    execute in build-* repository"
  echo "    layer_root directory with layer checkouts"
  echo "    U|C|T|P  U update weboslayers.py with layer HEADs"
  echo "             C commit modified weboslayers.py with right commit message"
  echo "             T tag latest revision in given branch with given tag"
  echo "             P push given tag name in all layers"
  echo "    mcf_file file to update instead of weboslayers.py"
  echo "    mcf_files other mcf files to update at the same time"
  echo "    layer_list space separated list of layers to update/commit e.g. 'meta meta-oe meta-lg-webos'"
  echo ""
  echo "Example:"
  echo "~/ow is directory where I keep layer checkouts"
  echo ""
  echo "This updates build-webos/weboslayers.py with latest HEADs in selected layers:"
  echo "~/ow/build-webos$ scripts/layerpin.sh ~/ow U meta-webos bitbake meta meta-oe"
  echo ""
  echo "This prepares commit message with all layerpins"
  echo "~/ow/build-webos$ scripts/layerpin.sh ~/ow C meta-webos bitbake meta meta-oe"
  echo ""
  echo "This creates new tag builds/foo/bar/42 pointing to current weboslayers.py revision in all private repos (will skip upstream and g2g repos)"
  echo "~/ow/build-webos$ scripts/layerpin.sh ~/ow T builds/foo/bar/42 meta-webos bitbake meta meta-oe"
  echo ""
  echo "This will push tag builds/foo/bar/42 in all private repos (will skip upsream and g2g repos)"
  echo "~/ow/build-webos$ scripts/layerpin.sh ~/ow P builds/foo/bar/42 meta-webos bitbake meta meta-oe"
  exit 1
fi

# change to name of remote (e.g. g, g2g or gpro), but it's expected that the same value is valid for all layers
# or leave empty and define rules in get_remote()
UPSTREAM_REMOTE=""

# change to non-empty if you want to run git remote update before git log
RUN_REMOTE_UPDATE=""

#GIT="/OE/git/bin/git"
GIT="git"

LAYER_ROOT=$1
shift
OPERATION=$1
shift
if [ "${OPERATION}" = "T" -o "${OPERATION}" = "P" ]; then
  TAG=$1
  shift
fi
MCFFILE=$1
shift
MCFFILES=$1
shift
LAYERS=$*

if [ "${OPERATION}" = "RU" ]; then
  RUN_REMOTE_UPDATE="y"
  OPERATION="U"
fi

BUILD=`pwd`
WEBOSLAYERS="${BUILD}/$MCFFILE"

COMMIT_MESSAGE="${BUILD}/commit-message"

get_remote() {
  if [ -n "${UPSTREAM_REMOTE}" ]; then
    echo ${UPSTREAM_REMOTE}
  elif [ "${L}" = "bitbake" -o "${L}" = "meta" -o "${L}" = "meta-oe" -o "${L}" = "meta-qt5" ]; then
    echo "origin"
  else
    echo "g"
  fi
}

get_layer_dir() {
  if [ "$1" = "meta" ]; then
    echo "oe-core"
  elif [ "$1" = "meta-oe" ]; then
    echo "meta-oe/meta-oe"
  elif [ "$1" = "meta-optee" ]; then
    echo "meta-linaro"
  else
    echo $1
  fi
}

get_branch() {
  LAYER=$1
  [ "${LAYER}" = "meta-lg-webos" ] && LAYER=meta-webos
  [ "${LAYER}" = "meta-webosose" ] && LAYER=meta-webos
  if grep -q "'${LAYER}'.*{'branch': OE_Branch" ${WEBOSLAYERS}; then
    BRANCH=`grep ^OE_Branch.= ${WEBOSLAYERS} | sed "s/OE_Branch = '\([^']*\)['].*$/\1/g"`
  elif grep -q "'${LAYER}'.*{'branch': Bitbake_Branch" ${WEBOSLAYERS}; then
    BRANCH=`grep ^Bitbake_Branch.= ${WEBOSLAYERS} | sed "s/Bitbake_Branch = '\([^']*\)['].*$/\1/g"`
  else
    BRANCH=`grep "'${LAYER}'.*{'branch': '" ${WEBOSLAYERS} | sed "s/^.*'${LAYER}'.*{'branch': '\([^']*\)['].*$/\1/g"`
  fi
  if [ -z "${BRANCH}" ]; then
    # default is master
    BRANCH=master
  fi
  echo ${BRANCH}
}

latest_commit() {
  DIR=`get_layer_dir $1`
  cd ${LAYER_ROOT}/${DIR}
  REMOTE=`get_remote $1`
  if [ -n "${RUN_REMOTE_UPDATE}" ]; then
    echo "=== Updating $1 ===" >&2
    ${GIT} remote update >&2
  fi
  NEW=`${GIT} log -1 --pretty=format:"%h" ${REMOTE}/$2`
  cd ${BUILD}
  echo ${NEW}
}

update_layer() {
  # sed on mac doesn't work correctly without -i argument (needs at least empty one, but empty one doesn't work without space to separate -i and argument)
  # sed -i 's/test/foo/g' test.txt or sed -i"" 's/test/foo/g' test.txt
  # sed: 1: "test.txt": undefined label 'est.txt'
  # to make it a bit worse GNU sed doesn't allow space between -i and argument
  # sed -i.bak 's/test/foo/g' test.txt -> OK
  LAYER=$1
  [ "${LAYER}" = "meta-lg-webos" ] && LAYER=meta-webos
  [ "${LAYER}" = "meta-webosose" ] && LAYER=meta-webos
  sed -i.bak "s/\(^.*'${LAYER}'.*'commit': '\)[^']*\(['].*$\)/\1$2\2/g" ${WEBOSLAYERS}
  sed -i.bak "s/\(^.*'${LAYER}'.*'commit': '\)[^']*\(['].*$\)/\1$2\2/g" ${BUILD}/${MCFFILES}
}

log_layer() {
  LAYER=$1
  [ "${LAYER}" = "meta-lg-webos" ] && LAYER=meta-webos
  [ "${LAYER}" = "meta-webosose" ] && LAYER=meta-webos
  OLD=`${GIT} diff ${WEBOSLAYERS} | grep "^-.*'${LAYER}'.*'commit': '" | sed "s/^.*'${LAYER}'.*'commit': '\([^']*\)['].*$/\1/g"`
  NEW=`${GIT} diff ${WEBOSLAYERS} | grep "^+.*'${LAYER}'.*'commit': '" | sed "s/^.*'${LAYER}'.*'commit': '\([^']*\)['].*$/\1/g"`
  if [ "${OLD}" != "${NEW}" ] ; then
    REMOTE=`get_remote $1`
    DIR=`get_layer_dir $1`
    cd ${LAYER_ROOT}/${DIR}
    CD=`${GIT} log -1 --date=iso --pretty=format:"%cd" ${REMOTE}/$2`
    LAYERNAME=$1
    [ "${LAYERNAME}" = "meta" ] && LAYERNAME=oe-core
    echo "${LAYERNAME} as of ${CD}" | tee -a ${COMMIT_MESSAGE}
    echo | tee -a ${COMMIT_MESSAGE}
    # fmt doesn't allow to set indentation of next lines, so replace it with sed (assuming that fmt on mac does the same)
    ${GIT} log --oneline ${OLD}..${NEW} | fmt -t -w 72 | sed 's/^   /        /g' | tee -a ${COMMIT_MESSAGE}
    echo | tee -a ${COMMIT_MESSAGE}
    cd ${BUILD}
  fi
}

tag_layer() {
  DIR=`get_layer_dir $1`
  TAG=$2
  COMMIT=$3
  cd ${LAYER_ROOT}/${DIR}
    echo "Tagging layer $1, commit ${COMMIT}, tag ${TAG}"
    ${GIT} tag -a -m "${TAG}" ${TAG} ${COMMIT}
  cd ${BUILD}
}

push_tag_in_layer() {
  DIR=`get_layer_dir $1`
  TAG=$2
  cd ${LAYER_ROOT}/${DIR}
    REMOTE=`get_remote $1`
    echo "Pushing tag ${TAG} to remote ${REMOTE}"
    ${GIT} push ${REMOTE} ${TAG}
  cd ${BUILD}
}

if [ ! -e "${WEBOSLAYERS}" ] ; then
  echo "${WEBOSLAYERS} does not exist, are you runnig this script from build-* checkout?"
  exit 1
fi

if [ -e "${COMMIT_MESSAGE}" ] ; then
  echo "${COMMIT_MESSAGE} already exists, removing it to create new one"
  rm -f ${COMMIT_MESSAGE}
fi

if [ "${OPERATION}" != "U" -a "${OPERATION}" != "C" -a "${OPERATION}" != "T" -a "${OPERATION}" != "P" ] ; then
  echo "Invalid operation, only 'U' update, 'C' commit, 'T' tag and 'P' push are supported"
  exit 1
fi

for L in ${LAYERS}; do
  LAYER=$1
  [ "${LAYER}" = "meta-lg-webos" ] && LAYER=meta-webos
  [ "${LAYER}" = "meta-webosose" ] && LAYER=meta-webos
  DIR=`get_layer_dir $1`
  if [ ! -d ${LAYER_ROOT}/${DIR}/.git ]; then
    echo "${LAYER_ROOT}/${DIR}/.git does not exist, is it valid checkout?"
    exit 1
  fi
  if ! grep -q "'${LAYER}'.*'commit': '" ${WEBOSLAYERS}; then
    echo "Layer '${LAYER}' does not have pinned commit"
  fi
done

# everything seems OK

if [ "${OPERATION}" = "U" ]; then
  for L in ${LAYERS}; do
    BRANCH=`get_branch ${L}`
    HEAD=`latest_commit ${L} ${BRANCH}`
    update_layer ${L} ${HEAD}
  done
fi

if [ "${OPERATION}" = "C" ]; then
  DISTRO=`grep ^Distribution ${WEBOSLAYERS} | sed 's/.*"\([^"]*\)"$/\1/g'`
  DATE=`date "+%Y-%m-%d"`
  SUBJECT="${DISTRO}-image"
  if [ -n "${MCFFILE}" ] ; then
    SUBJECT=`echo ${MCFFILE} | sed 's/webos-melodic-/webos-*-/g; s/ros1-melodic-/ros*-/g'`
  fi
  echo "${SUBJECT}: Update layer pins for ${DATE} e" | tee ${COMMIT_MESSAGE}
  echo | tee -a ${COMMIT_MESSAGE}
  for L in ${LAYERS}; do
    BRANCH=`get_branch ${L}`
    log_layer ${L} ${BRANCH}
  done
  echo "Log was generated in ${BUILD}/commit-mesage file, press ENTER to run"
  echo "${GIT} commit ${MCFFILES} -F ${COMMIT_MESSAGE}"
  read REPLY
  ${GIT} commit ${MCFFILES} -t ${COMMIT_MESSAGE}
fi

if [ "${OPERATION}" = "T" -o "${OPERATION}" = "P" ]; then
  if ! echo ${TAG} | grep -q "^builds/"; then
    echo "ERROR: Tag parameter doesn't start with builds/"
  fi
  for L in ${LAYERS}; do
    if [ "${L}" = "bitbake" -o "${L}" = "meta" -o "${L}" = "meta-oe" -o "${L}" = "meta-webos" -o "${L}" = "meta-webos-backports" ] ; then
      echo "INFO: skipping tag in public repo: ${L}"
      continue
    fi
    if [ "${OPERATION}" = "T" ] ; then
      LAYER=$1
      [ "${LAYER}" = "meta-lg-webos" ] && LAYER=meta-webos
      COMMIT=`grep "^('${LAYER}'.*commit=" ${WEBOSLAYERS} | sed "s/^('${LAYER}'.*commit=\([^',]*\)[',].*$/\1/g"`
      tag_layer ${L} ${TAG} ${COMMIT}
    fi
    if [ "${OPERATION}" = "P" ] ; then
      push_tag_in_layer ${L} ${TAG}
    fi
  done
fi

