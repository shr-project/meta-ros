# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_foxy
inherit ros_superflore_generated

DESCRIPTION = "gazebo_ros2_control"
AUTHOR = "Alejandro Hernandez <ahcorde@osrfoundation.org>"
ROS_AUTHOR = "Jonathan Bohren"
HOMEPAGE = "http://ros.org/wiki/gazebo_ros_control"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "BSD & Apache License 2.0"
LICENSE = "BSD & Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "gazebo_ros2_control"
ROS_BPN = "gazebo_ros2_control"

ROS_BUILD_DEPENDS = " \
    angles \
    controller-manager \
    gazebo-ros \
    gazebo-rosdev \
    hardware-interface \
    pluginlib \
    rclcpp \
    std-msgs \
    urdf \
    yaml-cpp-vendor \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    angles \
    controller-manager \
    gazebo-ros \
    gazebo-rosdev \
    hardware-interface \
    pluginlib \
    rclcpp \
    std-msgs \
    urdf \
    yaml-cpp-vendor \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    angles \
    controller-manager \
    gazebo-ros \
    gazebo-rosdev \
    hardware-interface \
    pluginlib \
    rclcpp \
    std-msgs \
    urdf \
    yaml-cpp-vendor \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-gtest \
    ament-lint-auto \
    ament-lint-common \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/gazebo_ros2_control-release/archive/release/foxy/gazebo_ros2_control/0.0.3-1.tar.gz
ROS_BRANCH ?= "branch=release/foxy/gazebo_ros2_control"
SRC_URI = "git://github.com/ros2-gbp/gazebo_ros2_control-release;${ROS_BRANCH};protocol=https"
SRCREV = "b1af87524b093d47b2d425ab14d30d99740ee659"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
