# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "This package provides a recovery behavior for the navigation stack that attempts to clear space by reverting the costmaps used by the navigation stack to the static map outside of a given area."
AUTHOR = "David V. Lu!! <davidvlu@gmail.com>"
ROS_AUTHOR = "Eitan Marder-Eppstein"
HOMEPAGE = "http://wiki.ros.org/clear_costmap_recovery"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=16;endline=16;md5=01c2bc31767ccb3a68e12f02612b2a97"

ROS_CN = "navigation"
ROS_BPN = "clear_costmap_recovery"

ROS_BUILD_DEPENDS = " \
    cmake-modules \
    costmap-2d \
    libeigen \
    nav-core \
    pluginlib \
    roscpp \
    tf2-ros \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    costmap-2d \
    libeigen \
    nav-core \
    pluginlib \
    roscpp \
    tf2-ros \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    costmap-2d \
    libeigen \
    nav-core \
    pluginlib \
    roscpp \
    tf2-ros \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rostest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/navigation-release/archive/release/noetic/clear_costmap_recovery/1.17.0-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/clear_costmap_recovery"
SRC_URI = "git://github.com/ros-gbp/navigation-release;${ROS_BRANCH};protocol=https"
SRCREV = "9510cfa0f70c2f8bfeee954affbc2eb1be2a9db8"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
