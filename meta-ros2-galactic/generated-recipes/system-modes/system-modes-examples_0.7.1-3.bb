# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_galactic
inherit ros_superflore_generated

DESCRIPTION = "Simple example system and according launch files for the system_modes     package."
AUTHOR = "Arne Nordmann <arne.nordmann@bosch.com>"
HOMEPAGE = "https://micro.ros.org/docs/concepts/client_library/lifecycle_and_system_modes/"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "Apache License 2.0"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=11;endline=11;md5=12c26a18c7f493fdc7e8a93b16b7c04f"

ROS_CN = "system_modes"
ROS_BPN = "system_modes_examples"

ROS_BUILD_DEPENDS = " \
    rclcpp \
    rclcpp-lifecycle \
    system-modes \
    system-modes-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    rclcpp \
    rclcpp-lifecycle \
    system-modes \
    system-modes-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    rclcpp \
    rclcpp-lifecycle \
    ros2launch \
    system-modes \
    system-modes-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-cppcheck \
    ament-cmake-cpplint \
    ament-cmake-flake8 \
    ament-cmake-gmock \
    ament-cmake-gtest \
    ament-cmake-pep257 \
    ament-cmake-uncrustify \
    ament-lint-auto \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/system_modes-release/archive/release/galactic/system_modes_examples/0.7.1-3.tar.gz
ROS_BRANCH ?= "branch=release/galactic/system_modes_examples"
SRC_URI = "git://github.com/ros2-gbp/system_modes-release;${ROS_BRANCH};protocol=https"
SRCREV = "45f82718c384afae1115f7dc49b6c972b04b7d75"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
