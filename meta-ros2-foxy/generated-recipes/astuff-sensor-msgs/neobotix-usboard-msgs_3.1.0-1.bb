# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_foxy
inherit ros_superflore_generated

DESCRIPTION = "neobotix_usboard package"
AUTHOR = "AutonomouStuff Software Team <software@autonomoustuff.com>"
ROS_AUTHOR = "Sam Rustan <srustan@autonomoustuff.com>"
HOMEPAGE = "http://wiki.ros.org/neobotix_usboard_msgs"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=58e54c03ca7f821dd3967e2a2cd1596e"

ROS_CN = "astuff_sensor_msgs"
ROS_BPN = "neobotix_usboard_msgs"

ROS_BUILD_DEPENDS = " \
    builtin-interfaces \
    ros-environment \
    rosidl-default-generators \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    builtin-interfaces \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    builtin-interfaces \
    rosidl-default-runtime \
    std-msgs \
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

# matches with: https://github.com/astuff/astuff_sensor_msgs-release/archive/release/foxy/neobotix_usboard_msgs/3.1.0-1.tar.gz
ROS_BRANCH ?= "branch=release/foxy/neobotix_usboard_msgs"
SRC_URI = "git://github.com/astuff/astuff_sensor_msgs-release;${ROS_BRANCH};protocol=https"
SRCREV = "b2a8c2823eee08f681041d9ebb5425505ea139a2"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
