# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "franka_hw provides hardware interfaces for using Franka Emika research robots with ros_control"
AUTHOR = "Franka Emika GmbH <support@franka.de>"
ROS_AUTHOR = "Franka Emika GmbH"
HOMEPAGE = "http://wiki.ros.org/franka_hw"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "Apache 2.0"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "franka_ros"
ROS_BPN = "franka_hw"

ROS_BUILD_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    combined-robot-hw \
    controller-interface \
    franka-msgs \
    hardware-interface \
    joint-limits-interface \
    libfranka \
    message-generation \
    pluginlib \
    roscpp \
    urdf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    combined-robot-hw \
    controller-interface \
    franka-msgs \
    hardware-interface \
    joint-limits-interface \
    libfranka \
    pluginlib \
    roscpp \
    urdf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    combined-robot-hw \
    controller-interface \
    franka-msgs \
    hardware-interface \
    joint-limits-interface \
    libfranka \
    pluginlib \
    roscpp \
    urdf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    franka-description \
    gtest \
    rostest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/frankaemika/franka_ros-release/archive/release/melodic/franka_hw/0.7.1-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/franka_hw"
SRC_URI = "git://github.com/frankaemika/franka_ros-release;${ROS_BRANCH};protocol=https"
SRCREV = "d246bbf16d81ecffd39641b682df2ab2985d7d05"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
