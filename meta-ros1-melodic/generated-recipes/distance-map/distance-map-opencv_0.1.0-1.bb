# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Node package of the map_distance project.     It contains the ROS node that instantiate the     distance map plugin."
AUTHOR = "Jeremie Deray <deray.jeremie@gmail.com>"
ROS_AUTHOR = "Sai Kishor Kothakota <sai.kishor@pal-robotics.com>"
HOMEPAGE = "https://github.com/opencv/opencv"
SECTION = "devel"
LICENSE = "Apache-2.0 & BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=14;endline=14;md5=e6ea7b8e0b94c2fb650aa51f54494b24"

ROS_CN = "distance_map"
ROS_BPN = "distance_map_opencv"

ROS_BUILD_DEPENDS = " \
    distance-map-core \
    image-geometry \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    distance-map-core \
    image-geometry \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    distance-map-core \
    image-geometry \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/artivis/distance_map-release/archive/release/melodic/distance_map_opencv/0.1.0-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/distance_map_opencv"
SRC_URI = "git://github.com/artivis/distance_map-release;${ROS_BRANCH};protocol=https"
SRCREV = "d9d6b8c7fca4b31c8701654b4ea995b897f43bdf"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
