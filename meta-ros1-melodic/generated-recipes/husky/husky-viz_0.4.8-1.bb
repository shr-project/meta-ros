# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Visualization configuration for Clearpath Husky"
AUTHOR = "Tony Baltovski <tbaltovski@clearpathrobotics.com>"
ROS_AUTHOR = "Ryan Gariepy <rgariepy@clearpathrobotics.com>"
HOMEPAGE = "http://ros.org/wiki/husky_viz"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "husky"
ROS_BPN = "husky_viz"

ROS_BUILD_DEPENDS = " \
    roslaunch \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    husky-description \
    joint-state-publisher \
    joint-state-publisher-gui \
    robot-state-publisher \
    rviz \
    rviz-imu-plugin \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    husky-description \
    joint-state-publisher \
    joint-state-publisher-gui \
    robot-state-publisher \
    rviz \
    rviz-imu-plugin \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/clearpath-gbp/husky-release/archive/release/melodic/husky_viz/0.4.8-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/husky_viz"
SRC_URI = "git://github.com/clearpath-gbp/husky-release;${ROS_BRANCH};protocol=https"
SRCREV = "8349049249ff263ff356202134588960ab30afc1"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
