# Copyright (c) 2020 LG Electronics, Inc.

export GTEST_DIR = "${STAGING_EXECPREFIXDIR}"
export GMOCK_DIR = "${STAGING_EXECPREFIXDIR}"

inherit python3native
ROS_TEST_DEPENDS += "rosidl-adapter rosidl-adapter-native python3-numpy-native rosidl-generator-c-native rosidl-generator-cpp-native rosidl-typesupport-introspection-cpp-native rosidl-typesupport-introspection-c-native rosidl-typesupport-fastrtps-cpp-native rosidl-typesupport-cpp-native rosidl-typesupport-fastrtps-c-native rosidl-generator-py-native"
