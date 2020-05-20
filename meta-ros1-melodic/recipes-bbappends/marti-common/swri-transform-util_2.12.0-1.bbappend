# Copyright (c) 2020 LG Electronics, Inc.

EXTRA_OECMAKE += "-Dswri_nodelet_SHARE=${RECIPE_SYSROOT}${ros_datadir}/swri_nodelet"

# Fails with boost-1.69.0
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += "file://0001-utm_util.h-don-t-make-UtmData-private.patch"
# Copyright (c) 2020 LG Electronics, Inc.
  
do_configure_append() {
    # Fixes this:
    # swri-transform-util/2.12.0-1-r0/recipe-sysroot/usr/include/c++/8.2.0/cstdlib:75:15: fatal error: stdlib.h: No such file or directory
    #  #include_next <stdlib.h>
    sed -i 's/-isystem /-I/g' ${B}/build.ninja
}
