# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "rqt_bag provides a GUI plugin for displaying and replaying ROS bag files."
AUTHOR = "Mabel Zhang <mabel@openrobotics.org>"
ROS_AUTHOR = "Dirk Thomas <dthomas@osrfoundation.org>"
HOMEPAGE = "http://wiki.ros.org/rqt_bag"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rqt_bag"
ROS_BPN = "rqt_bag"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${PYTHON_PN}-rospkg \
    python-qt-binding \
    rosbag \
    rosgraph-msgs \
    roslib \
    rosnode \
    rospy \
    rqt-gui \
    rqt-gui-py \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/rqt_bag-release/archive/release/melodic/rqt_bag/0.5.0-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/rqt_bag"
SRC_URI = "git://github.com/ros-gbp/rqt_bag-release;${ROS_BRANCH};protocol=https"
SRCREV = "b3cfd77713754c15b7c48c34fefdd57555d3858a"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}