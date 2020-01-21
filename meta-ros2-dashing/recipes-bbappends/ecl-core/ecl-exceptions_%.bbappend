# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

#ERROR: ecl-exceptions-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/dashing/lib/ecl_exceptions/demo_exceptions contained in package ecl-exceptions requires libecl_exceptions.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-exceptions? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
