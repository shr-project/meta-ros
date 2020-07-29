# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "Various tools for IMU devices"
AUTHOR = "Martin Günther <martin.guenther1980@gmail.com>"
HOMEPAGE = "http://ros.org/wiki/imu_tools"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d81febe1baeaed0bfd599be384185f36"

ROS_CN = "imu_tools"
ROS_BPN = "imu_tools"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    imu-complementary-filter \
    imu-filter-madgwick \
    rviz-imu-plugin \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    imu-complementary-filter \
    imu-filter-madgwick \
    rviz-imu-plugin \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/uos-gbp/imu_tools-release/archive/release/noetic/imu_tools/1.2.2-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/imu_tools"
SRC_URI = "git://github.com/uos-gbp/imu_tools-release;${ROS_BRANCH};protocol=https"
SRCREV = "4caf4a9f3f8347de3cc36d8624ed2ff9f8ed35c1"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
