# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "multisense_cal_check"
AUTHOR = "Carnegie Robotics <support@carnegierobotics.com>"
HOMEPAGE = "https://github.com/carnegierobotics/multisense_ros"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "multisense_ros"
ROS_BPN = "multisense_cal_check"

ROS_BUILD_DEPENDS = " \
    multisense-ros \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    multisense-ros \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    multisense-ros \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/carnegieroboticsllc/multisense_ros-release/archive/release/noetic/multisense_cal_check/4.0.4-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/multisense_cal_check"
SRC_URI = "git://github.com/carnegieroboticsllc/multisense_ros-release;${ROS_BRANCH};protocol=https"
SRCREV = "e73ccbe8559ea15367e62b9a66350c7f192103c4"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
