# Copyright (c) 2020 LG Electronics, Inc.

ROS_EXPORT_DEPENDS += " \
    rosidl-generator-c \
    rosidl-typesupport-c \
"
ROS_TEST_DEPENDS_remove = "${@bb.utils.contains('ROS_WORLD_SKIP_GROUPS', 'connext', 'rosidl-typesupport-connext-c', '', d)}"

ROS_TEST_DEPENDS += "fastrtps-cmake-module python3-numpy-native rosidl-generator-cpp-native rosidl-typesupport-fastrtps-cpp-native rosidl-typesupport-fastrtps-c-native rosidl-generator-py-native"
