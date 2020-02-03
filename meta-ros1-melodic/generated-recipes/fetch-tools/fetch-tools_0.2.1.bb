# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Commands for performing common operations when   developing on the robots. For help, run `fetch -h` and `fetch   COMMAND -h`."
AUTHOR = "Russell Toris <rtoris@fetchrobotics.com>"
ROS_AUTHOR = "Alex Henning"
HOMEPAGE = "https://github.com/fetchrobotics/fetch_tools/blob/master/README.md"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "fetch_tools"
ROS_BPN = "fetch_tools"

ROS_BUILD_DEPENDS = " \
    python-rospkg \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-argcomplete} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python-catkin-lint} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_sshpass} \
    roslint \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/fetchrobotics-gbp/fetch_tools-release/archive/release/melodic/fetch_tools/0.2.1-0.tar.gz
ROS_BRANCH ?= "branch=release/melodic/fetch_tools"
SRC_URI = "git://github.com/fetchrobotics-gbp/fetch_tools-release;${ROS_BRANCH};protocol=https"
SRCREV = "fcc9af1a02c8561698864412fa9136e656c59049"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
