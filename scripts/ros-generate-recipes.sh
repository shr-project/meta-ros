# /bin/sh (deliberately no !#)
#
# Usage: cd meta-ros
#        [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/ros-generate-recipes.sh CMD ARGS ...
#        (current branch is now superflore/DATETIME)
#            or
#        sh scripts/ros-generate-recipes.sh --version
#
# The recognized CMD ARGS are:
#
#         from-release ROS_DISTRO YYYYMMDD PATH-TO-LOCAL-ROSDISTRO ROSDISTRO-COMMIT
#             - Generate all the recipes for the YYYYMMDD release of ROS_DISTRO from the specified commit of a local
#               ros/rosdistro.git . If running prior to first release of ROS_DISTRO, specify "none" for YYYYMMDD. NB. The release
#               date might not match the commit timestamp of ROSDISTRO-COMMIT; however, the commit timestamp is used for the
#               DATETIME when forming the name of the created branch.
#
#         single PATH-TO-LOCAL-ROSDISTRO ROSDISTRO-COMMIT
#             - Generate the recipe changed by ROSDISTRO-COMMIT. The ROS_DISTRO value is inferred. (Not yet implemented.)
#
#         regen ROS_DISTRO [ROS_PKG1 ROS_PKG2 ...]
#             - Re-generate the recipes for the specified packages from the existing ROS_DISTRO/cache.yaml. If no packages are
#               specified, re-generate all of the recipes. (Not yet implemented.)
#
# This script will abort if Git detects any uncommitted modifications, eg, from a previous run that did not complete or untracked
# files (which would otherwise appear in files/ROS_DISTRO/superflore-change-summary.txt).
#
# Copyright (c) 2019 LG Electronics, Inc.

SCRIPT_NAME="ros-generate-recipes"
SCRIPT_VERSION="1.0.0"

usage() {
    echo "Usage: cd meta-ros"
    echo "       [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/$SCRIPT_NAME.sh \\"
    echo "           from-release ROS_DISTRO YYYYMMDD PATH-TO-LOCAL-ROSDISTRO ROSDISTRO-COMMIT"
    echo "               or"
    echo "       sh scripts/$SCRIPT_NAME.sh --version"
    exit 1
}

if [ $1 = "--version" ]; then
    echo "$SCRIPT_NAME $SCRIPT_VERSION"
    exit
fi

[ $# -ne 5 ] && usage

[ -z "$SUPERFLORE_GEN_OE_RECIPES" ] && SUPERFLORE_GEN_OE_RECIPES=$(which superflore-gen-oe-recipes)
if [ -z "$SUPERFLORE_GEN_OE_RECIPES" ]; then
    echo "ABORT: superflore-gen-oe-recipes not found"
    exit
fi

[ $1 != "from-release" ] && usage

ROS_DISTRO=$2
# ROS_VERSION and ROS_PYTHON_VERSION must be in the environment as they appear in "conditional" attributes.
case $ROS_DISTRO in
    "kinetic"|"melodic")
        export ROS_VERSION="1"
        export ROS_PYTHON_VERSION="2"
        ;;

    "crystal"|"dashing")
        export ROS_VERSION="2"
        export ROS_PYTHON_VERSION="3"
        ;;

    *)  echo "ABORT: Unrecognized ROS_DISTRO: $ROS_DISTRO"
        exit 1
        ;;
esac

ROS_DISTRO_RELEASE_DATE=$3
case $ROS_DISTRO_RELEASE_DATE in
    none|[2-9][0-9][0-9][0-9][0-1][0-9][0-3][0-9])
        : OK
        ;;

    *)  echo "ABORT: ROS_DISTRO_RELEASE_DATE not YYYYMMDD or 'none': '$3'"
        exit 1
        ;;
esac

if [ ! -d $4 ]; then
    echo "ABORT: '$4' not found"
    exit 1
fi

if [ -n "$(git status --porcelain=v1)" ]; then
    echo "ABORT: Uncommitted modifications detected by Git -- perhaps invoke 'git reset --hard'?"
    exit 1
fi

cd $4
path_to_rosdistro=$PWD
rev=$(git rev-list -1 $5) || exit 1
commit_timestamp=$(git log -1 --date=iso-strict --format=%cd $rev)
# Associate the upcoming superflore run with the commit date of <ROSDISTRO-COMMIT>.
export SUPERFLORE_GENERATION_DATETIME=$(date +%Y%m%d%H%M%S --utc -d $commit_timestamp)
unset commit_timestamp
cd - > /dev/null

if [ -n "$(git branch --list superflore/$SUPERFLORE_GENERATION_DATETIME)" ]; then
    echo "ABORT: Branch 'superflore/$SUPERFLORE_GENERATION_DATETIME' already exists"
    exit 1
fi

skip_keys_option=""
ros1_lisp_packages="euslisp geneus genlisp roslisp actionlib_lisp cl_tf cl_tf2 cl_transforms cl_transforms_stamped cl_urdf cl_utils roslisp_common roslisp_utilities rosemacs ros_emacs_utils roslisp_repl slime_ros slime_wrapper"
case $ROS_DISTRO in
    "kinetic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers grpc nanomsg octovis rosdoc_lite"
        skip_keys_option="$skip_keys_option $ros1_lisp_packages"
        ;;

    "melodic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers iirob_filters grpc nanomsg octovis rosdoc_lite"
        skip_keys_option="$skip_keys_option $ros1_lisp_packages"
        ;;

    *)  : Nothing is skipped for "crystal" and "dashing".
        ;;
esac

only_option=""
# XXX Eventually:
if [ $1 = "regen" ]; then
    shift 2
    [ $# -gt 0 ] && only_option="--only $*"
fi

tmpdir=$(mktemp -t -d ros-generate-recipes-XXXXXXXX)
trap "rm -rf $tmpdir" 0

# Create a directory tree under $tmpdir with the contents of ros/rosdistro.git at commit $rev.
cd $path_to_rosdistro
git archive $rev | tar -C $tmpdir -xf -
cd - > /dev/null

# Create $tmpdir/$ROS_DISTRO-cache.yaml.gz .
cd $tmpdir

# XXX Fix up a package that's been renamed. Only needed if generating from a commit prior to 2019-09-05.
false && \
sed -i -e 's/micro-xrce-dds-agent:/microxrcedds_agent:/' \
       -e 's@https://github.com/micro-ROS/Micro-XRCE-DDS-Agent-release.git@https://github.com/micro-ROS/microxrcedds_agent-release.git@' \
       $ROS_DISTRO/distribution.yaml

rosdistro_build_cache --preclean --ignore-local $tmpdir/index-v4.yaml $ROS_DISTRO

# Fixup the index there to use the newly created $ROS_DISTRO-cache.yaml.gz .
sed -i -e "/$ROS_DISTRO-cache.yaml.gz/ s@: .*\$@: file://$tmpdir/$ROS_DISTRO-cache.yaml.gz@" index-v4.yaml
cd - > /dev/null

# Tell superflore to use this index instead of the upstream one.
export ROSDISTRO_INDEX_URL="file://$tmpdir/index-v4.yaml"

rosdep update || { echo "ABORT: 'rosdep update' failed"; exit 1; }

before_commit=$(git rev-list -1 HEAD)
$SUPERFLORE_GEN_OE_RECIPES --dry-run --ros-distro $ROS_DISTRO --output-repository-path . --upstream-branch HEAD \
                            $skip_keys_option $only_option

after_commit=$(git rev-list -1 HEAD)
if [ $after_commit != $before_commit ]; then
    # Identify how the files were generated so that they can be reused.
    sed -i -e "1 s/\$/ $ROS_DISTRO_RELEASE_DATE $rev/" files/$ROS_DISTRO/cache.yaml
    sed -i -e "1 s/\$/ $ROS_DISTRO_RELEASE_DATE $rev/" files/$ROS_DISTRO/cache.diffme
    git add files/$ROS_DISTRO/cache.yaml files/$ROS_DISTRO/cache.diffme

    generated="conf/ros-distro/include/$ROS_DISTRO/generated-ros-distro.inc"
    [ $ROS_DISTRO_RELEASE_DATE = "none" ] && ROS_DISTRO_RELEASE_DATE=""
    cat <<! >> $generated

# From the release announcement or the last field of the "release-ROS_DISTRO-YYYYMMDD" tag for the release in
# https://github.com/ros2/ros2/releases. Prior to the first release of a ROS_DISTRO, it is set to "".
ROS_DISTRO_RELEASE_DATE = "$ROS_DISTRO_RELEASE_DATE"

# The commit of rosdistro/$ROS_DISTRO/distribution.yaml from which the recipes were generated.
ROS_SUPERFLORE_GENERATION_COMMIT = "$rev"
!
    git add $generated
    git commit --amend -q -C HEAD

    unset generated
fi
