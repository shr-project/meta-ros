# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "Message and service types: custom messages and services for TurtleBot3 packages"
AUTHOR = "Will Son <willson@robotis.com>"
ROS_AUTHOR = "Pyo <pyo@robotis.com>"
HOMEPAGE = "http://wiki.ros.org/turtlebot3_msgs"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "turtlebot3_msgs"
ROS_BPN = "turtlebot3_msgs"

ROS_BUILD_DEPENDS = " \
    message-generation \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    message-runtime \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    message-runtime \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ROBOTIS-GIT-release/turtlebot3_msgs-release/archive/release/noetic/turtlebot3_msgs/1.0.1-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/turtlebot3_msgs"
SRC_URI = "git://github.com/ROBOTIS-GIT-release/turtlebot3_msgs-release;${ROS_BRANCH};protocol=https"
SRCREV = "6aa8799a0d94d44aba8ec316f0dc6ee6f6b571d3"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
