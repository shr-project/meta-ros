# Copyright (c) 2020 LG Electronics, Inc.

# ERROR: ecl-converters-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_converters/demo_float_converters contained in package ecl-converters requires libecl_exceptions.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-converters? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
