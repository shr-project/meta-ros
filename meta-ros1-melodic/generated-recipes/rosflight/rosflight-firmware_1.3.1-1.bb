# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Firmware library for software-in-the-loop of the ROSflight ROS stack"
AUTHOR = "Daniel Koch <daniel.p.koch@gmail.com>"
ROS_AUTHOR = "Daniel Koch <daniel.p.koch@gmail.com>"
HOMEPAGE = "http://rosflight.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rosflight"
ROS_BPN = "rosflight_firmware"

ROS_BUILD_DEPENDS = " \
    roscpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    roscpp \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    roscpp \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/rosflight/rosflight-release/archive/release/melodic/rosflight_firmware/1.3.1-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/rosflight_firmware"
SRC_URI = "git://github.com/rosflight/rosflight-release;${ROS_BRANCH};protocol=https"
SRCREV = "94b36ebeab9c47a2b2045fe5b8ce7b677400a7d0"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
