# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "imu_pipeline"
AUTHOR = "Paul Bovbel <pbovbel@clearpathrobotics.com>"
ROS_AUTHOR = "Chad Rockey <chadrockey@gmail.com>"
HOMEPAGE = "http://ros.org/wiki/imu_pipeline"
SECTION = "devel"
LICENSE = "BSD & GPL-1"
LIC_FILES_CHKSUM = "file://package.xml;beginline=6;endline=6;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "imu_pipeline"
ROS_BPN = "imu_pipeline"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    imu-processors \
    imu-transformer \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    imu-processors \
    imu-transformer \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/imu_pipeline-release/archive/release/melodic/imu_pipeline/0.2.3-0.tar.gz
ROS_BRANCH ?= "branch=release/melodic/imu_pipeline"
SRC_URI = "git://github.com/ros-gbp/imu_pipeline-release;${ROS_BRANCH};protocol=https"
SRCREV = "b390a349ba58798e15bca4e42bbf0ad1750173d7"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
