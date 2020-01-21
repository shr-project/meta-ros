# Copyright (c) 2019-2020 LG Electronics, Inc.

# ERROR: ament-download-0.0.1-1-r0 do_package_qa: QA Issue: /usr/share/ament_download/cmake/download_checkmd5.py contained in package ament-download-dev requires /bin/env, but no providers found in RDEPENDS_ament-download-dev? [file-rdeps]
# RDEPENDS_${PN}-dev += "coreutils"

do_install_append() {
    sed -i 's@^#!/bin/env@/usr/bin/env@g' ${D}${ros_datadir}/ament_download/cmake/download_checkmd5.py
}
