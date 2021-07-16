# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_galactic
inherit ros_superflore_generated

DESCRIPTION = "Smac global planning plugin"
AUTHOR = "Steve Macenski <stevenmacenski@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=82f0323c08605e5b6f343b05213cf7cc"

ROS_CN = "navigation2"
ROS_BPN = "nav2_smac_planner"

ROS_BUILD_DEPENDS = " \
    builtin-interfaces \
    ceres-solver \
    eigen3-cmake-module \
    geometry-msgs \
    libeigen \
    nav-msgs \
    nav2-common \
    nav2-core \
    nav2-costmap-2d \
    nav2-msgs \
    nav2-util \
    ompl \
    pluginlib \
    rclcpp \
    rclcpp-action \
    rclcpp-lifecycle \
    tf2-ros \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    builtin-interfaces \
    ceres-solver \
    eigen3-cmake-module \
    geometry-msgs \
    libeigen \
    nav-msgs \
    nav2-common \
    nav2-core \
    nav2-costmap-2d \
    nav2-msgs \
    nav2-util \
    ompl \
    pluginlib \
    rclcpp \
    rclcpp-action \
    rclcpp-lifecycle \
    tf2-ros \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    builtin-interfaces \
    ceres-solver \
    eigen3-cmake-module \
    geometry-msgs \
    libeigen \
    nav-msgs \
    nav2-common \
    nav2-core \
    nav2-costmap-2d \
    nav2-msgs \
    nav2-util \
    ompl \
    pluginlib \
    rclcpp \
    rclcpp-action \
    rclcpp-lifecycle \
    tf2-ros \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-lint-auto \
    ament-lint-common \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/SteveMacenski/navigation2-release/archive/release/galactic/nav2_smac_planner/1.0.0-2.tar.gz
ROS_BRANCH ?= "branch=release/galactic/nav2_smac_planner"
SRC_URI = "git://github.com/SteveMacenski/navigation2-release;${ROS_BRANCH};protocol=https"
SRCREV = "c8356d37e034c951b624708630ba6ef84ef6bdae"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
