# Copyright (c) 2020 LG Electronics, Inc.

DEPENDS += "gtest"

#EXTRA_OECMAKE += "-DGTEST_DIR=${RECIPE_SYSROOT}"

export GTEST_DIR = "${STAGING_EXECPREFIXDIR}"
export GMOCK_DIR = "${STAGING_EXECPREFIXDIR}"

DEPENDS += "python-cmake-module"
inherit python3native
