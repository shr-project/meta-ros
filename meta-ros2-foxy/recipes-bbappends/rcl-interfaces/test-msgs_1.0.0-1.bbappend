# Copyright (c) 2020 LG Electronics, Inc.

ROS_BUILD_DEPENDS += " \
    rosidl-typesupport-fastrtps-c-native \
    rosidl-typesupport-fastrtps-cpp-native \
"

export GTEST_DIR = "${STAGING_EXECPREFIXDIR}"
export GMOCK_DIR = "${STAGING_EXECPREFIXDIR}"
