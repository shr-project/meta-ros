# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

# ERROR: ecl-formatters-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_formatters/demo_formatters contained in package ecl-formatters requires libecl_formatters.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-formatters? [file-rdeps]
# ERROR: ecl-formatters-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_formatters/demo_formatters contained in package ecl-formatters requires libecl_exceptions.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-formatters? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
