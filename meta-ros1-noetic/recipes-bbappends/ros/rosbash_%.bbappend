# Copyright (c) 2019 LG Electronics, Inc.

# "rosrun" uses array variables, so we can't use the BASH provided by "busybox" but must use a "real" one. NB. "busybox" is only
# present on the target.
do_install_append_class-target() {
    sed -i -e '1 s@^.*$@#!/usr/bin/env bash-real@' ${D}${ros_bindir}/rosrun
}

RDEPENDS_${PN}_append_class-target = " bash-real"
