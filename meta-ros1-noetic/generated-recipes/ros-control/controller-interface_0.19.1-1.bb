# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "Interface base class for controllers."
AUTHOR = "Bence Magyar <bence.magyar.robotics@gmail.com>"
ROS_AUTHOR = "Wim Meeussen"
HOMEPAGE = "https://github.com/ros-controls/ros_control/wiki"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=14;endline=14;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "ros_control"
ROS_BPN = "controller_interface"

ROS_BUILD_DEPENDS = " \
    hardware-interface \
    roscpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    hardware-interface \
    roscpp \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    roscpp \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/ros_control-release/archive/release/noetic/controller_interface/0.19.1-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/controller_interface"
SRC_URI = "git://github.com/ros-gbp/ros_control-release;${ROS_BRANCH};protocol=https"
SRCREV = "8f78558494eb9cbffdddc6e17a1102e7bb736397"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
