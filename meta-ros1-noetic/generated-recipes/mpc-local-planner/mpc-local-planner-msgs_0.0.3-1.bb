# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "This package provides message types that are used by the package mpc_local_planner"
AUTHOR = "Christoph Rösmann <christoph.roesmann@tu-dortmund.de>"
ROS_AUTHOR = "Christoph Rösmann <christoph.roesmann@tu-dortmund.de>"
HOMEPAGE = "http://wiki.ros.org/mpc_local_planner_msgs"
SECTION = "devel"
LICENSE = "GPL-3"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=1e7b3bcc2e271699c77c769685058cbe"

ROS_CN = "mpc_local_planner"
ROS_BPN = "mpc_local_planner_msgs"

ROS_BUILD_DEPENDS = " \
    message-generation \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    message-runtime \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/rst-tu-dortmund/mpc_local_planner-release/archive/release/noetic/mpc_local_planner_msgs/0.0.3-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/mpc_local_planner_msgs"
SRC_URI = "git://github.com/rst-tu-dortmund/mpc_local_planner-release;${ROS_BRANCH};protocol=https"
SRCREV = "7940c6d23475eecef98ba277b1736134cc1ca760"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
