# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

# ERROR: ecl-errors-1.0.3-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_errors/demo_errors contained in package ecl-errors requires libecl_errors.so.1.0.3()(64bit), but no providers found in RDEPENDS_ecl-errors? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
