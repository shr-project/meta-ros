# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "The cob_collision_velocity_filter package is a package for collision avoidance using teleoperation."
AUTHOR = "Felipe Garcia Lopez <flg@ipa.fhg.de>"
ROS_AUTHOR = "Matthias Gruhler"
HOMEPAGE = "http://ros.org/wiki/cob_collision_velocity_filter"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "Apache 2.0"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=6;endline=6;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "cob_control"
ROS_BPN = "cob_collision_velocity_filter"

ROS_BUILD_DEPENDS = " \
    boost \
    cob-footprint-observer \
    costmap-2d \
    dynamic-reconfigure \
    geometry-msgs \
    nav-msgs \
    roscpp \
    tf \
    tf2-ros \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    boost \
    cob-footprint-observer \
    costmap-2d \
    dynamic-reconfigure \
    geometry-msgs \
    nav-msgs \
    roscpp \
    tf \
    tf2-ros \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    boost \
    cob-footprint-observer \
    costmap-2d \
    dynamic-reconfigure \
    geometry-msgs \
    nav-msgs \
    roscpp \
    tf \
    tf2-ros \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ipa320/cob_control-release/archive/release/noetic/cob_collision_velocity_filter/0.8.13-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/cob_collision_velocity_filter"
SRC_URI = "git://github.com/ipa320/cob_control-release;${ROS_BRANCH};protocol=https"
SRCREV = "d6dc5b9eff52561683bc8ec9760851efd454f750"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
