# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "Javascript ROS message and service generators."
AUTHOR = "Chris Smith <csmith@rethinkrobotics.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "gennodejs"
ROS_BPN = "gennodejs"

ROS_BUILD_DEPENDS = " \
    genmsg \
"

ROS_BUILDTOOL_DEPENDS = " \
    ${PYTHON_PN}-setuptools-native \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    genmsg \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/sloretz/gennodejs-release/archive/release/noetic/gennodejs/2.0.2-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/gennodejs"
SRC_URI = "git://github.com/sloretz/gennodejs-release;${ROS_BRANCH};protocol=https"
SRCREV = "4c9b60d1dbe17f4da9a4d63523d7df91fc92f8ef"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
