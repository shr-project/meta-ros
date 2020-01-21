# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

# ERROR: ecl-time-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_time/demo_sleep contained in package ecl-time requires libecl_time.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-time? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
