# Copyright (c) 2019 LG Electronics, Inc.

ROS_BUILDTOOL_DEPENDS += " \
    python3-empy-native \
    rcutils-native \
"

export GTEST_DIR = "${STAGING_EXECPREFIXDIR}"
export GMOCK_DIR = "${STAGING_EXECPREFIXDIR}"

ROS_TEST_DEPENDS += "rosidl-adapter-native python3-numpy-native rosidl-generator-c-native rosidl-generator-cpp-native rosidl-typesupport-c-native rosidl-typesupport-fastrtps-cpp-native rosidl-typesupport-cpp-native rosidl-typesupport-introspection-cpp-native rosidl-typesupport-fastrtps-c-native rosidl-generator-py-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += "file://0001-test-don-t-prefix-test_resources_dir_name-with-CMAKE.patch"

FILES_${PN} += "${prefix}/test"
