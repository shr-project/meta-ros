# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_noetic
inherit ros_superflore_generated

DESCRIPTION = "3D visualization tool for ROS."
AUTHOR = "William Woodall <william@osrfoundation.org>"
ROS_AUTHOR = "Dave Hershberger"
HOMEPAGE = "http://wiki.ros.org/rviz"
SECTION = "devel"
LICENSE = "BSD & CC-BY-SA-3.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "rviz"
ROS_BPN = "rviz"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libogre-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libogre} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libqt5-opengl-dev} \
    assimp \
    cmake-modules \
    geometry-msgs \
    image-transport \
    interactive-markers \
    laser-geometry \
    libeigen \
    libtinyxml2 \
    map-msgs \
    mesa \
    message-filters \
    message-generation \
    nav-msgs \
    pluginlib \
    python-qt-binding \
    qtbase \
    resource-retriever \
    rosbag \
    rosconsole \
    roscpp \
    roslib \
    rospy \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf2-geometry-msgs \
    tf2-ros \
    urdf \
    urdfdom \
    urdfdom-headers \
    visualization-msgs \
    yaml-cpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libogre-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libogre} \
    assimp \
    geometry-msgs \
    image-transport \
    interactive-markers \
    laser-geometry \
    libtinyxml2 \
    map-msgs \
    mesa \
    message-filters \
    nav-msgs \
    pluginlib \
    python-qt-binding \
    resource-retriever \
    rosbag \
    rosconsole \
    roscpp \
    roslib \
    rospy \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf2-geometry-msgs \
    tf2-ros \
    urdf \
    visualization-msgs \
    yaml-cpp \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libogre} \
    assimp \
    geometry-msgs \
    image-transport \
    interactive-markers \
    laser-geometry \
    libtinyxml2 \
    map-msgs \
    media-export \
    mesa \
    message-filters \
    message-runtime \
    nav-msgs \
    pluginlib \
    python-qt-binding \
    qtbase \
    resource-retriever \
    rosbag \
    rosconsole \
    roscpp \
    roslib \
    rospy \
    sensor-msgs \
    std-msgs \
    std-srvs \
    tf2-geometry-msgs \
    tf2-ros \
    urdf \
    visualization-msgs \
    yaml-cpp \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rostest \
    rosunit \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/rviz-release/archive/release/noetic/rviz/1.14.1-1.tar.gz
ROS_BRANCH ?= "branch=release/noetic/rviz"
SRC_URI = "git://github.com/ros-gbp/rviz-release;${ROS_BRANCH};protocol=https"
SRCREV = "4274b49a50033cceb7c0e20e46246c889ff98177"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
