# Generated by superflore -- DO NOT EDIT

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Library for capturing data from the Intel(R) RealSense(TM) SR300, D400 Depth cameras and T2xx Tracking devices. This effort was initiated to better support researchers, creative coders, and app developers in domains such as robotics, virtual reality, and the internet of things. Several often-requested features of RealSense(TM); devices are implemented in this project."
AUTHOR = "Sergey Dorodnicov <sergey.dorodnicov@intel.com>"
ROS_AUTHOR = "Sergey Dorodnicov <sergey.dorodnicov@intel.com>"
HOMEPAGE = "https://github.com/IntelRealSense/librealsense/"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=25;endline=25;md5=e8978a5103d23266fc6f8ec03dc9eb16"

ROS_CN = "librealsense2"
ROS_BPN = "librealsense2"

ROS_BUILD_DEPENDS = " \
    libusb1 \
    openssl \
    pkgconfig \
    udev \
    virtual/kernel \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    libusb1 \
    openssl \
    udev \
    virtual/kernel \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    libusb1 \
    openssl \
    udev \
    virtual/kernel \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/IntelRealSense/librealsense2-release/archive/release/melodic/librealsense2/2.31.0-3.tar.gz
ROS_BRANCH ?= "branch=release/melodic/librealsense2"
SRC_URI = "git://github.com/IntelRealSense/librealsense2-release;${ROS_BRANCH};protocol=https"
SRCREV = "71d9a17cd43ec35b6df0413818e037b59a16febe"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "cmake"

inherit ros_${ROS_BUILD_TYPE}
