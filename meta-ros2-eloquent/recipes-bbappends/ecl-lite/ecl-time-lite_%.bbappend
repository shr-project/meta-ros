# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

# ERROR: ecl-time-lite-1.0.3-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_time_lite/demo_time_functions contained in package ecl-time-lite requires libecl_time_lite.so.1.0.3()(64bit), but no providers found in RDEPENDS_ecl-time-lite? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
