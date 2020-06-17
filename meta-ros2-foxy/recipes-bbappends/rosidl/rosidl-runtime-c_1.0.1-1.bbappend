# Copyright (c) 2020 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += " \
    file://debug.patch \
"
