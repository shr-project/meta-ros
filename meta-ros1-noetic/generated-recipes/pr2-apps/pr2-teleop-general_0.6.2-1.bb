# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "pr2_teleop_general"
AUTHOR = "ROS Orphaned Package Maintainers <ros-orphaned-packages@googlegroups.com>"
ROS_AUTHOR = "Gil Jones"
HOMEPAGE = "http://ros.org/wiki/pr2_teleop_general"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "pr2_apps"
ROS_BPN = "pr2_teleop_general"

ROS_BUILD_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    angles \
    geometry-msgs \
    moveit-msgs \
    polled-camera \
    pr2-common-action-msgs \
    pr2-controller-manager \
    pr2-controllers-msgs \
    pr2-mechanism-msgs \
    pr2-msgs \
    ps3joy \
    roscpp \
    sensor-msgs \
    tf \
    urdf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    angles \
    geometry-msgs \
    moveit-msgs \
    polled-camera \
    pr2-arm-kinematics \
    pr2-common-action-msgs \
    pr2-controller-manager \
    pr2-controllers-msgs \
    pr2-mannequin-mode \
    pr2-mechanism-msgs \
    pr2-msgs \
    pr2-tuck-arms-action \
    ps3joy \
    roscpp \
    sensor-msgs \
    tf \
    urdf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    actionlib \
    actionlib-msgs \
    angles \
    geometry-msgs \
    moveit-msgs \
    polled-camera \
    pr2-arm-kinematics \
    pr2-common-action-msgs \
    pr2-controller-manager \
    pr2-controllers-msgs \
    pr2-mannequin-mode \
    pr2-mechanism-msgs \
    pr2-msgs \
    pr2-tuck-arms-action \
    ps3joy \
    roscpp \
    sensor-msgs \
    tf \
    urdf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    roslaunch \
    rostest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/pr2-gbp/pr2_apps-release/archive/release/noetic/pr2_teleop_general/0.6.2-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/pr2_teleop_general"
SRC_URI = "git://github.com/pr2-gbp/pr2_apps-release;${ROS_BRANCH};protocol=https"
SRCREV = "82af52297508c3bdc5baf9d54f2f0f3e630ca648"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
