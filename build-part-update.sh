#!/bin/bash

if [ $# -ne 1 ] ; then
    echo "Usage: $0 directory to build"
    exit
fi
ID=`basename $1`
shift

export BUILD_DATE=`date "+%Y-%m-%d_%H-%M-%S"`

OE_DISTRO=`echo $ID | cut -d- -f1`
ROS_DISTRO=`echo $ID | cut -d- -f2`
OE_RELEASE=`echo $ID | cut -d- -f3`
JOB_TYPE=`echo $ID | cut -d- -f4`

if [ -z "${MCF_META_ROS_NO_OVERRIDES}" ] ; then
    export MCF_META_ROS_REPO_URL=https://github.com/shr-project/meta-ros.git
    export MCF_META_ROS_WEBOS_REPO_URL=https://github.com/shr-project/meta-ros-webos.git

    # set to empty to track latest revision in given branch
    export MCF_META_ROS_COMMIT=""
    export MCF_META_ROS_WEBOS_COMMIT=""

    export MCF_META_ROS_BRANCH="${OE_RELEASE}-next"
    [ "${OE_RELEASE}" = "kirkstone" ] && export MCF_META_ROS_BRANCH="master-next"
fi

case "${OE_DISTRO}" in
    ros1|ros2|webos)
        ;;
    ros1_webos|ros2_webos)
        OE_DISTRO="webos"
        ;;
    *)
        echo "ERROR: unknown OE_DISTRO ${OE_DISTRO} in ID $ID"
        exit 1
        ;;
esac

case "${ROS_DISTRO}" in
    melodic|noetic|crystal|dashing|eloquent|foxy|galactic|rolling)
        ;;
    *)
        echo "ERROR: unknown ROS_DISTRO ${ROS_DISTRO} in ID $ID"
        exit 1
        ;;
esac

case "${OE_RELEASE}" in
    thud|warrior|zeus|dunfell|gatesgarth|hardknott|honister|kirkstone)
        ;;
    *)
        echo "ERROR: unknown OE_RELEASE ${OE_RELEASE} in ID $ID"
        exit 1
        ;;
esac

MCF="${OE_DISTRO}-${ROS_DISTRO}-${OE_RELEASE}"

if [ -z "${MACHINE}" ] || [ -z "${MACHINES}" ] ; then
    export MACHINE="qemux86"
    export MACHINES="${MACHINE} raspberrypi4"
    [ "${OE_DISTRO}" = "webos" ] && MACHINE="qemux86-64" && MACHINES="${MACHINE} raspberrypi4-64"
fi

[ -z "${IMAGES}" ] && [ -z "${TARGETS}" ] && export IMAGES="world"

if [ "${JOB_TYPE}" = "world" ] ; then
    export IMAGES=""
    export TARGETS="world"
fi

export MACHINE

echo "Building ${ID} OE_DISTRO ${OE_DISTRO} ROS_DISTRO ${ROS_DISTRO} OE_RELEASE ${OE_RELEASE} MACHINES ${MACHINES} IMAGES ${IMAGES} TARGETS ${TARGETS}"

if [ -z "${JOB_NAME}" ] ; then
    [ -d ${ID} ] || mkdir ${ID}
    cd ${ID}
    META_ROS_BUILD_DIR=../meta-ros-build
else
    # Workspace for jenkins job has own checkout of meta-ros-build
    # while local builds have one meta-ros-build in parant directory
    # and separate ${ID} directory for each local build job
    META_ROS_BUILD_DIR=meta-ros-build
    # Also use SSTATE_DIR and DL_DIR on NFS, instead of SSTATE_MIRROR and PREMIRROR
    NFS_IS_RW="true"
fi

[ -d conf ] || mkdir conf
[ -d build ] || mkdir build
ln -snf ../conf build/conf
rm -f log.mcf

if [ ! -e openembedded-core/oe-init-build-env ] ; then
    ${META_ROS_BUILD_DIR}/scripts/mcf -f ${META_ROS_BUILD_DIR}/files*/${MCF}.mcf --clean 2>&1 | tee -a log.mcf
fi
if [ ! -e openembedded-core/oe-init-build-env ] ; then
    echo "ERROR: `pwd`/openembedded-core/oe-init-build-env doesn't exist, did the mcf update fail?"
    exit 1
fi

. openembedded-core/oe-init-build-env
cd ..

declare -i RESULT=0

${META_ROS_BUILD_DIR}/scripts/mcf -f ${META_ROS_BUILD_DIR}/files*/${MCF}.mcf --clean 2>&1 | tee -a log.mcf
RESULT+=${PIPESTATUS[0]}

if [ "${RESULT}" -ne 0 ] ; then
    echo "ERROR: mcf update failed"
    exit 1
fi
