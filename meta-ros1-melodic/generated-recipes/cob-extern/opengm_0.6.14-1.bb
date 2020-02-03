# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "This package wraps the external c++ library opengm in a ROS package, so other packages can use it. It downloads the source code of it and then unzips it. The library is a header-only library with command line interfaces, which aren't used, so it doesn't gets build. For further descriptions and tutorials see the Makefile.tarball and https://github.com/opengm/opengm . 		Copyright (C) 2013 Bjoern Andres, Thorsten Beier and Joerg H.~Kappes."
AUTHOR = "Richard Bormann <richard.bormann@ipa.fraunhofer.de>"
ROS_AUTHOR = "Florian Jordan <florian.jordan@ipa.fraunhofer.de>"
HOMEPAGE = "http://hciweb2.iwr.uni-heidelberg.de/opengm/"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://package.xml;beginline=13;endline=13;md5=1a58895467feb5b42724abbf3f0d9612"

ROS_CN = "cob_extern"
ROS_BPN = "opengm"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = ""

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ipa320/cob_extern-release/archive/release/melodic/opengm/0.6.14-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/opengm"
SRC_URI = "git://github.com/ipa320/cob_extern-release;${ROS_BRANCH};protocol=https"
SRCREV = "a0dbbd519e93e9f2ce073c7a2dabe3d526b778a7"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
