# Copyright (c) 2019 LG Electronics, Inc.

ROS_BUILD_DEPENDS_remove = "ament-cmake"
ROS_BUILDTOOL_DEPENDS += "ament-cmake-native"

ROS_TEST_DEPENDS += "ament-cmake-cppcheck ament-cmake-cpplint ament-cmake-flake8 ament-cmake-pep257 ament-cmake-uncrustify ament-cmake-xmllint ament-copyright-native ament-lint-cmake-native ament-xmllint-native"
