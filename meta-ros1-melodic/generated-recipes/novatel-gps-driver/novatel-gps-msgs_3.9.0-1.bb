# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Messages for proprietary (non-NMEA) sentences from Novatel GPS receivers."
AUTHOR = "P. J. Reed <preed@swri.org>"
HOMEPAGE = "https://ivs-git.dyn.datasys.swri.edu/uss/novatel_gps.git"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "Southwest Research Institute Proprietary"
LICENSE = "Southwest-Research-Institute-Proprietary"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=0ecd2b809a714729aa61f2072c17e9ad"

ROS_CN = "novatel_gps_driver"
ROS_BPN = "novatel_gps_msgs"

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

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/swri-robotics-gbp/novatel_gps_driver-release/archive/release/melodic/novatel_gps_msgs/3.9.0-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/novatel_gps_msgs"
SRC_URI = "git://github.com/swri-robotics-gbp/novatel_gps_driver-release;${ROS_BRANCH};protocol=https"
SRCREV = "8d357ae0509420db59bab81c205735888cb6237b"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
