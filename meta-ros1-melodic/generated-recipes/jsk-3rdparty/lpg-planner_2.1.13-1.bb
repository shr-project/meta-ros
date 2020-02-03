# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "lpg_planner: LPGL Planner (http://zeus.ing.unibs.it/lpg/)"
AUTHOR = "Hitoshi Kamada <h-kamada@jsk.imi.i.u-tokyo.ac.jp>"
ROS_AUTHOR = "Kei Okada <k-okada@jsk.imi.i.u-tokyo.ac.jp>"
HOMEPAGE = "http://ros.org/wiki/downward"
SECTION = "devel"
LICENSE = "GPL-1"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=162b49cfbae9eadf37c9b89b2d2ac6be"

ROS_CN = "jsk_3rdparty"
ROS_BPN = "lpg_planner"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = ""

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/tork-a/jsk_3rdparty-release/archive/release/melodic/lpg_planner/2.1.13-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/lpg_planner"
SRC_URI = "git://github.com/tork-a/jsk_3rdparty-release;${ROS_BRANCH};protocol=https"
SRCREV = "8d4aabd37d2faf469f0c7fa9c7279f245c555f7d"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
