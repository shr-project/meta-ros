# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "Improved ROS message filters"
AUTHOR = "Timo Röhling <timo.roehling@fkie.fraunhofer.de>"
ROS_AUTHOR = "Timo Röhling <timo.roehling@fkie.fraunhofer.de>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=82f0323c08605e5b6f343b05213cf7cc"

ROS_CN = "fkie_message_filters"
ROS_BPN = "fkie_message_filters"

ROS_BUILD_DEPENDS = " \
    image-transport \
    roscpp \
    sensor-msgs \
    tf2-ros \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    image-transport \
    roscpp \
    sensor-msgs \
    tf2-ros \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    image-transport \
    roscpp \
    sensor-msgs \
    tf2-ros \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/fkie-release/message_filters-release/archive/release/noetic/fkie_message_filters/1.0.1-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/fkie_message_filters"
SRC_URI = "git://github.com/fkie-release/message_filters-release;${ROS_BRANCH};protocol=https"
SRCREV = "b61ada8295a8fac5044e7d24d7a2e9d16adce7b3"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
