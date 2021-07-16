# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Topic tools for treating messages as variant types."
AUTHOR = "Samuel Bachmann <sbachmann@anybotics.com>"
ROS_AUTHOR = "Ralf Kaestner <ralf.kaestner@gmail.com>"
HOMEPAGE = "http://github.com/anybotics/variant"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "GNU Lesser General Public License (LGPL)"
LICENSE = "GNU-Lesser-General-Public-License-LGPL-"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=146ba316845cfe6058c8baebd902a726"

ROS_CN = "variant"
ROS_BPN = "variant_topic_tools"

ROS_BUILD_DEPENDS = " \
    roscpp \
    roslib \
    variant-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    roscpp \
    roslib \
    variant-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    roscpp \
    roslib \
    variant-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    geometry-msgs \
    gtest \
    std-msgs \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/anybotics/variant-release/archive/release/melodic/variant_topic_tools/0.1.6-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/variant_topic_tools"
SRC_URI = "git://github.com/anybotics/variant-release;${ROS_BRANCH};protocol=https"
SRCREV = "041879c97773b77aea70bee69018d171f3182961"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
