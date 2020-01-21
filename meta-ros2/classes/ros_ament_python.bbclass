# Copyright (c) 2018-2019 LG Electronics, Inc.

inherit setuptools3

DISTUTILS_STAGE_HEADERS_ARGS = "--install-dir=${STAGING_INCDIR}/${PYTHON_DIR}"
DISTUTILS_STAGE_ALL_ARGS = "--prefix=${STAGING_DIR_HOST}${ros_prefix} \
    --install-data=${STAGING_DATADIR}"
DISTUTILS_INSTALL_ARGS = "--root=${D} \
    --prefix=${ros_prefix} \
    --install-lib=${PYTHON_SITEPACKAGES_DIR} \
    --install-data=${ros_datadir}"

do_install_append() {
    mkdir -p ${D}${ros_datadir}/ament_index/resource_index/packages
    touch ${D}${ros_datadir}/ament_index/resource_index/packages/${ROS_BPN}
    if test -d ${D}${ros_libdir}/${ROS_BPN} && ls ${D}${ros_bindir}/${ROS_BPN}/* >/dev/null 2>/dev/null; then
        for i in ${D}${ros_libdir}/${ROS_BPN}/* ; do \
            sed -i '1c#!/usr/bin/env python3' $i
        done
    fi
    if ls ${D}${ros_bindir}/* >/dev/null 2>/dev/null; then
        for i in ${D}${ros_bindir}/* ; do \
            sed -i '1c#!/usr/bin/env python3' $i
        done
    fi
}

FILES_${PN}_prepend = " \
    ${ros_datadir}/ament_index \
"
