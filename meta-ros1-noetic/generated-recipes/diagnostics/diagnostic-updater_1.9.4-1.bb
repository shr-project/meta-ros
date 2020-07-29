# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "diagnostic_updater contains tools for easily updating diagnostics. it is commonly used in device drivers to keep track of the status of output topics, device status, etc."
AUTHOR = "Austin Hendrix <namniart@gmail.com>"
ROS_AUTHOR = "Kevin Watts"
HOMEPAGE = "http://www.ros.org/wiki/diagnostic_updater"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "diagnostics"
ROS_BPN = "diagnostic_updater"

ROS_BUILD_DEPENDS = " \
    diagnostic-msgs \
    roscpp \
    rostest \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    diagnostic-msgs \
    roscpp \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    diagnostic-msgs \
    roscpp \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/diagnostics-release/archive/release/noetic/diagnostic_updater/1.9.4-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/diagnostic_updater"
SRC_URI = "git://github.com/ros-gbp/diagnostics-release;${ROS_BRANCH};protocol=https"
SRCREV = "9d0802380a699e4e55ed6335c933477a36baa3a8"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
