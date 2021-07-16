# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "The ros client for roboception tag detection modules"
AUTHOR = "Felix Ruess <felix.ruess@roboception.de>"
ROS_AUTHOR = "Monika Florek-Jasinska <monika.florek-jasinska@roboception.de>"
HOMEPAGE = "http://wiki.ros.org/rc_tagdetect_client"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rc_visard"
ROS_BPN = "rc_tagdetect_client"

ROS_BUILD_DEPENDS = " \
    curl \
    dynamic-reconfigure \
    geometry-msgs \
    message-generation \
    rc-common-msgs \
    rcdiscover \
    roscpp \
    std-srvs \
    tf \
    tf2-geometry-msgs \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    curl \
    dynamic-reconfigure \
    geometry-msgs \
    rc-common-msgs \
    rcdiscover \
    roscpp \
    std-srvs \
    tf \
    tf2-geometry-msgs \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    curl \
    dynamic-reconfigure \
    geometry-msgs \
    message-runtime \
    rc-common-msgs \
    rcdiscover \
    roscpp \
    std-srvs \
    tf \
    tf2-geometry-msgs \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/roboception-gbp/rc_visard-release/archive/release/melodic/rc_tagdetect_client/3.1.0-2.tar.gz
ROS_BRANCH ?= "branch=release/melodic/rc_tagdetect_client"
SRC_URI = "git://github.com/roboception-gbp/rc_visard-release;${ROS_BRANCH};protocol=https"
SRCREV = "7b02b2ba14a19102ad16adf94f5e9d9b65ccd501"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
