# Generated by superflore -- DO NOT EDIT
#
# Copyright 2019 Open Source Robotics Foundation

inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "<p>       amcl is a probabilistic localization system for a robot moving in       2D. It implements the adaptive (or KLD-sampling) Monte Carlo       localization approach (as described by Dieter Fox), which uses a       particle filter to track the pose of a robot against a known map.     </p>     <p>       This node is derived, with thanks, from Andrew Howard's excellent       'amcl' Player driver.     </p>"
AUTHOR = "Mohammad Haghighipanah <mohammad.haghighipanah@intel.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://package.xml;beginline=21;endline=21;md5=d94c5c8f30151b2fe7d07ba53ed6444b"

ROS_CN = "navigation2"
ROS_BPN = "nav2_amcl"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    launch-ros \
    launch-testing \
    message-filters \
    nav-msgs \
    nav2-common \
    nav2-util \
    rclcpp \
    sensor-msgs \
    std-srvs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    geometry-msgs \
    launch-ros \
    launch-testing \
    message-filters \
    nav-msgs \
    nav2-util \
    rclcpp \
    sensor-msgs \
    std-srvs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    launch-ros \
    launch-testing \
    message-filters \
    nav-msgs \
    nav2-util \
    rclcpp \
    sensor-msgs \
    std-srvs \
    tf2 \
    tf2-geometry-msgs \
    tf2-ros \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-cmake-gtest \
    ament-cmake-pytest \
    ament-lint-auto \
    ament-lint-common \
    launch \
    launch-testing \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

SRC_URI = "https://github.com/SteveMacenski/navigation2-release/archive/release/dashing/nav2_amcl/0.2.4-1.tar.gz;downloadfilename=${ROS_SP}.tar.gz"
SRC_URI[md5sum] = "9f685cbb08abe049b2b5ea78bb596dba"
SRC_URI[sha256sum] = "63cd9413e0313edbe59ba5ba198f1147b298e1e6a580600a483512aa5d46a6ee"
S = "${WORKDIR}/navigation2-release-release-dashing-nav2_amcl-0.2.4-1"

ROS_COMPONENT_TYPE = "${@ros_distro__get_component_type('navigation2', d)}"
ROS_BUILD_TYPE = "ament_cmake"

# Allow the above settings to be overridden.
ROS_INCLUDES_TREE := "${@ros_superflore_generated__get_includes_tree('navigation2', d)}"
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation2/navigation2_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation2/navigation2-${PV}_common.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation2/${BPN}.inc
include ${ROS_LAYERDIR}/${ROS_INCLUDES_TREE}/navigation2/${BPN}-${PV}.inc

inherit ${ROS_COMPONENT_TYPE}_component
inherit ros_${ROS_BUILD_TYPE}
