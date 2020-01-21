# Copyright (c) 2019-2020 LG Electronics, Inc.

# Create a ros.sh that sources ros/setup.sh, thereby setting up the ROS workspace for every login. Place it in a separate package
# which will be added to images when IMAGE_FEATURES contains "ros-implicit-workspace"
do_install_append() {
    install -d ${D}${sysconfdir}/profile.d
    echo ". $prefix/setup.sh" > ${D}${sysconfdir}/profile.d/ros.sh
}

PACKAGES =+ "${PN}-implicitworkspace"

FILES_${PN}-implicitworkspace = " \
    ${sysconfdir}/profile.d/ros.sh \
"

FILES_${PN} += "${ros_prefix}"
