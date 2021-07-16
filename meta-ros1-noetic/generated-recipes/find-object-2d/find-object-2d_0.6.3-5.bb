# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "The find_object_2d package"
AUTHOR = "Mathieu Labbe <matlabbe@gmail.com>"
ROS_AUTHOR = "Mathieu Labbe <matlabbe@gmail.com>"
HOMEPAGE = "http://find-object.googlecode.com"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "find_object_2d"
ROS_BPN = "find_object_2d"

ROS_BUILD_DEPENDS = " \
    cv-bridge \
    image-transport \
    message-filters \
    message-generation \
    qtbase \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    cv-bridge \
    image-transport \
    message-filters \
    message-runtime \
    qtbase \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    cv-bridge \
    image-transport \
    message-filters \
    message-runtime \
    qtbase \
    roscpp \
    rospy \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/introlab/find_object_2d-release/archive/release/noetic/find_object_2d/0.6.3-5.tar.gz
ROS_BRANCH ?= "branch=release/noetic/find_object_2d"
SRC_URI = "git://github.com/introlab/find_object_2d-release;${ROS_BRANCH};protocol=https"
SRCREV = "65f6cda12da407642c1e34691b433829ff586d55"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
