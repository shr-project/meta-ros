# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Gazebo models and runtime configuration for igvc_self_drive simulator"
AUTHOR = "Micho Radovnikovich <mtradovn@oakland.edu>"
ROS_AUTHOR = "Micho Radovnikovich <mtradovn@oakland.edu>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
# Original license in package.xml, joined with "&" when multiple license tags were used:
#         "BSD2"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=6c4b0dfc2c040991f7798d2c24b8fc03"

ROS_CN = "igvc_self_drive_sim"
ROS_BPN = "igvc_self_drive_gazebo"

ROS_BUILD_DEPENDS = ""

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    gazebo-ros \
    robot-state-publisher \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    roslaunch \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/robustify/igvc_self_drive_sim-release/archive/release/melodic/igvc_self_drive_gazebo/0.1.4-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/igvc_self_drive_gazebo"
SRC_URI = "git://github.com/robustify/igvc_self_drive_sim-release;${ROS_BRANCH};protocol=https"
SRCREV = "ab1ee42af916927c68049e3f8f805870d4df7396"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
