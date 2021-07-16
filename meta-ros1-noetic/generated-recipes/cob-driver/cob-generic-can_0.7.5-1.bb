# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "The package cob_generic_can provides an interface for nodes on a can-bus and examplary wrappers for two PeakSys-can-libs. When a can-bus-device is generated (for an example see base_dirve_chain) you can use generic_can to create as many itfs as there will be components communicating via this can-bus. Assign type of the can communication device (e.g. usb-to-can or can-card of a specific vendor) and can-address of the target device. This package comes with wrappers for PeakSys and PeakSysUSB adapters."
AUTHOR = "Matthias Gruhler <mig@ipa.fhg.de>"
ROS_AUTHOR = "Christian Connette"
HOMEPAGE = "http://ros.org/wiki/cob_generic_can"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "Apache 2.0"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=6;endline=6;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "cob_driver"
ROS_BPN = "cob_generic_can"

ROS_BUILD_DEPENDS = " \
    cob-utilities \
    libntcan \
    libpcan \
    socketcan-interface \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    cob-utilities \
    libntcan \
    libpcan \
    socketcan-interface \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cob-utilities \
    libntcan \
    libpcan \
    socketcan-interface \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ipa320/cob_driver-release/archive/release/noetic/cob_generic_can/0.7.5-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/cob_generic_can"
SRC_URI = "git://github.com/ipa320/cob_driver-release;${ROS_BRANCH};protocol=https"
SRCREV = "081114eacf72c745b38e3f4914ca17fac0fba223"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
