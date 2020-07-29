# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "ifm pmd-based 3D ToF Camera ROS package"
AUTHOR = "Sean Kelly <sean.kelly@ifm.com>"
ROS_AUTHOR = "Tom Panzarella <tom@boxrobotics.ai>"
HOMEPAGE = "https://github.com/lovepark/ifm3d-ros"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=fc216ef9336537897fbeafa564601763"

ROS_CN = "ifm3d"
ROS_BPN = "ifm3d"

ROS_BUILD_DEPENDS = " \
    cv-bridge \
    ifm3d-core \
    image-transport \
    message-generation \
    nodelet \
    pcl-ros \
    roscpp \
    rospy \
    rostest \
    sensor-msgs \
    std-msgs \
    tf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    cv-bridge \
    ifm3d-core \
    image-transport \
    message-generation \
    nodelet \
    pcl-ros \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    tf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cv-bridge \
    ifm3d-core \
    image-transport \
    message-generation \
    nodelet \
    pcl-ros \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    tf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ifm/ifm3d-ros-release/archive/release/noetic/ifm3d/0.6.2-3.tar.gz
ROS_BRANCH ?= "branch=release/noetic/ifm3d"
SRC_URI = "git://github.com/ifm/ifm3d-ros-release;${ROS_BRANCH};protocol=https"
SRCREV = "0d8db58f56135dea741897500b80812b41f4202c"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
