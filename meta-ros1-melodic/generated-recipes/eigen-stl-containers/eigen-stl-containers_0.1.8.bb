# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "This package provides a set of typedef's that allow   using Eigen datatypes in STL containers"
AUTHOR = "Chris Lalancette <clalancette@osrfoundation.org>"
ROS_AUTHOR = "Ioan Sucan <isucan@willowgarage.com>"
HOMEPAGE = "http://eigen.tuxfamily.org/dox/TopicUnalignedArrayAssert.html"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "eigen_stl_containers"
ROS_BPN = "eigen_stl_containers"

ROS_BUILD_DEPENDS = " \
    cmake-modules \
    libeigen \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    libeigen \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    libeigen \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/eigen_stl_containers-release/archive/release/melodic/eigen_stl_containers/0.1.8-0.tar.gz
ROS_BRANCH ?= "branch=release/melodic/eigen_stl_containers"
SRC_URI = "git://github.com/ros-gbp/eigen_stl_containers-release;${ROS_BRANCH};protocol=https"
SRCREV = "e23ac2d84a7c6281fb3fffa0662fb73e11fb7cae"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
