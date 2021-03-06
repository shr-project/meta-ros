# Copyright (c) 2020 LG Electronics, Inc.

# ERROR: leo-teleop-1.1.0-1-r0 do_package_qa: QA Issue: /opt/ros/melodic/lib/leo_teleop/scripts/key_teleop contained in package leo-teleop requires /bin/bash, but no providers found in RDEPENDS_leo-teleop? [file-rdeps]
RDEPENDS_${PN} += "bash"

# This is needed only for webOS OSE and it's not in meta-ros-webos, because
# then meta-ros-webos would need to include this .bbappend just when
# meta-ros1-melodic layer is included
VIRTUAL-RUNTIME_bash ?= "bash"
RDEPENDS_${PN}_append_class-target_webos = " ${VIRTUAL-RUNTIME_bash}"
RDEPENDS_${PN}_remove_class-target_webos = "${@oe.utils.conditional('WEBOS_PREFERRED_PROVIDER_FOR_BASH', 'busybox', 'bash', '', d)}"
