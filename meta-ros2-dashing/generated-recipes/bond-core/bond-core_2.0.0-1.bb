# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_dashing
inherit ros_superflore_generated

DESCRIPTION = "A bond allows two processes, A and B, to know when the other has     terminated, either cleanly or by crashing. The bond remains     connected until it is either broken explicitly or until a     heartbeat times out."
AUTHOR = "Michael Carroll <michael@openrobotics.org>"
ROS_AUTHOR = "Stu Glaser"
HOMEPAGE = "http://www.ros.org/wiki/bond_core"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "bond_core"
ROS_BPN = "bond_core"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    bond \
    bondcpp \
    smclib \
    test-bond \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/bond_core-release/archive/release/dashing/bond_core/2.0.0-1.tar.gz
ROS_BRANCH ?= "branch=release/dashing/bond_core"
SRC_URI = "git://github.com/ros2-gbp/bond_core-release;${ROS_BRANCH};protocol=https"
SRCREV = "5137bdd8aafdea4b61c162e31994cb4cd4d60ee3"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}