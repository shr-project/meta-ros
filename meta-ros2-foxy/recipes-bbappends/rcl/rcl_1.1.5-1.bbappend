# Copyright (c) 2020 LG Electronics, Inc.

export GTEST_DIR = "${STAGING_EXECPREFIXDIR}"
export GMOCK_DIR = "${STAGING_EXECPREFIXDIR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += "file://0001-test-don-t-prefix-test_resources_dir_name-with-CMAKE.patch"

FILES_${PN} += "${prefix}/test"
