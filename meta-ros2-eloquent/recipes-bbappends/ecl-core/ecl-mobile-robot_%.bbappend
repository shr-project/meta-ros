# Copyright (c) 2019-2020 LG Electronics, Inc.

inherit ros_insane_dev_so

# ERROR: ecl-mobile-robot-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_mobile_robot/demo_partial_inverse contained in package ecl-mobile-robot requires libec _mobile_robot.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-mobile-robot? [file-rdeps]
# ERROR: ecl-mobile-robot-1.0.4-1-r0 do_package_qa: QA Issue: /opt/ros/eloquent/lib/ecl_mobile_robot/demo_partial_inverse contained in package ecl-mobile-robot requires libec _geometry.so.1.0.4()(64bit), but no providers found in RDEPENDS_ecl-mobile-robot? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
