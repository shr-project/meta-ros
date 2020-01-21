# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

# ERROR: ecl-io-1.0.3-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_io/demo_sockets contained in package ecl-io requires libecl_io.so.1.0.3()(64bit), but no providers found in RDEPENDS_ecl-io? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
