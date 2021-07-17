# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_galactic
inherit ros_superflore_generated

DESCRIPTION = "MoveIt interface to OMPL"
AUTHOR = "Dave Coleman <dave@picknik.ai>"
ROS_AUTHOR = "Ioan Sucan <isucan@google.com>"
HOMEPAGE = "http://moveit.ros.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=11;endline=11;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "moveit"
ROS_BPN = "moveit_planners_ompl"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_DEP-libomp-dev} \
    moveit-common \
    moveit-core \
    moveit-msgs \
    moveit-ros-planning \
    ompl \
    pluginlib \
    rclcpp \
    tf2-eigen \
    tf2-ros \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    eigen3-cmake-module-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_DEP-libomp-dev} \
    moveit-core \
    moveit-msgs \
    moveit-ros-planning \
    ompl \
    pluginlib \
    rclcpp \
    tf2-eigen \
    tf2-ros \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_DEP-libomp-dev} \
    moveit-core \
    moveit-msgs \
    moveit-ros-planning \
    ompl \
    pluginlib \
    rclcpp \
    tf2-eigen \
    tf2-ros \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-gtest \
    ament-lint-auto \
    ament-lint-common \
    libeigen \
    moveit-resources-fanuc-moveit-config \
    moveit-resources-panda-moveit-config \
    moveit-resources-pr2-description \
    tf2-eigen \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/moveit/moveit2-release/archive/release/galactic/moveit_planners_ompl/2.2.0-1.tar.gz
ROS_BRANCH ?= "branch=release/galactic/moveit_planners_ompl"
SRC_URI = "git://github.com/moveit/moveit2-release;${ROS_BRANCH};protocol=https"
SRCREV = "2ed53f1dc0b85df331bdcc42e80abccde57b3ae6"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
