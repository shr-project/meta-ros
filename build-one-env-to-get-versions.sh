#!/bin/bash

if [ $# -ne 1 ] ; then
    echo "Usage: $0 directory to build"
    exit
fi
ID=`basename $1`
shift

export BUILD_DATE=`date "+%Y-%m-%d_%H-%M-%S"`

export MCF_META_ROS_REPO_URL=git://github.com/shr-project/meta-ros.git
export MCF_META_ROS_WEBOS_REPO_URL=git://github.com/shr-project/meta-ros-webos.git

# set to empty to track latest revision in given branch
export MCF_META_ROS_COMMIT=""
export MCF_META_ROS_WEBOS_COMMIT=""

OE_DISTRO=`echo $ID | cut -d- -f1`
ROS_DISTRO=`echo $ID | cut -d- -f2`
OE_RELEASE=`echo $ID | cut -d- -f3`

export MCF_META_ROS_BRANCH="${OE_RELEASE}-next"
[ "${OE_RELEASE}" = "honister" ] && export MCF_META_ROS_BRANCH="master-next"

case "${OE_DISTRO}" in
    ros1|ros2|webos)
        ;;
    *)
        echo "ERROR: unknown OE_DISTRO ${OE_DISTRO} in ID $ID"
        exit 1
        ;;
esac

case "${ROS_DISTRO}" in
    melodic|noetic|crystal|dashing|eloquent|foxy|galactic|rolling)
        ;;
    *)
        echo "ERROR: unknown ROS_DISTRO ${ROS_DISTRO} in ID $ID"
        exit 1
        ;;
esac

case "${OE_RELEASE}" in
    thud|warrior|zeus|dunfell|gatesgarth|hardknott|honister)
        ;;
    *)
        echo "ERROR: unknown OE_RELEASE ${OE_RELEASE} in ID $ID"
        exit 1
        ;;
esac

export MACHINES="raspberrypi4-64 qemux86-64" # consider adding raspberrypi4 qemux86 qemuarm qemuarm64"
[ "${OE_DISTRO}" = "webos" ] && MACHINES="raspberrypi4 qemux86"
[ "${OE_DISTRO}" != "webos" -a "${OE_RELEASE}" = "thud" ] && MACHINES="raspberrypi3-64 qemux86-64"

export IMAGES="core world"
#[ "${ROS_DISTRO}" = "melodic" ] && IMAGES="turtlebot3-all"

export MACHINE

echo "Building ${ID} OE_DISTRO ${OE_DISTRO} ROS_DISTRO ${ROS_DISTRO} OE_RELEASE ${OE_RELEASE} MACHINES ${MACHINES} IMAGES ${IMAGES}"
[ -d ${ID} ] || mkdir ${ID}
cd ${ID}

[ -d conf ] || mkdir conf
[ -d build ] || mkdir build
ln -snf ../conf build/conf
rm -f log.mcf

if [ ! -e openembedded-core/oe-init-build-env ] ; then
    ../meta-ros-build/scripts/mcf -f ../meta-ros-build/files*/$ID.mcf --clean 2>&1 | tee -a log.mcf
fi
if [ ! -e openembedded-core/oe-init-build-env ] ; then
    echo "ERROR: `pwd`/openembedded-core/oe-init-build-env doesn't exist, did the mcf update fail?"
    exit 1
fi

. openembedded-core/oe-init-build-env
cd ..

declare -i RESULT=0

../meta-ros-build/scripts/mcf -f ../meta-ros-build/files*/$ID.mcf --clean 2>&1 | tee -a log.mcf
RESULT+=${PIPESTATUS[0]}

if [ "${RESULT}" -ne 0 ] ; then
    echo "ERROR: mcf update failed"
    exit 1
fi

echo "Create or re-create local.conf"
if [ -e conf/local.conf ] ; then
  # make a backup
  mv conf/local.conf conf/local.conf.${BUILD_DATE}.$$
fi
cp openembedded-core/meta/conf/local.conf.sample conf/local.conf
# Add local.conf additions from https://github.com/ros/meta-ros/wiki/OpenEmbedded-Build-Instructions
# with some modifications to ROS_COMMON_ARTIFACTS, TCLIBCAPPEND, BB_NUMBER_THREADS, PARALLEL_AMEK

ARTIFACTS_DIR="/mnt/mirror-write-ros/jansa"
ARTIFACTS_SSTATE_DIR="ros/ros-${OE_RELEASE}/sstate-cache"
[ "${OE_DISTRO}" = "webos" ] && ARTIFACTS_SSTATE_DIR="webos/ose-${OE_RELEASE}/sstate-cache"

cat >> conf/local.conf << EOF
# ROS-ADDITIONS-BEGIN
# ^^^^^^^^^^^^^^^^^^^ In the future, tools will expect to find this line.

# Increment the minor version whenever you add or change a setting in this file.
ROS_LOCAL_CONF_SETTINGS_VERSION = "2.1"

# If not using mcf, replace \${MCF_DISTRO} with the DISTRO being used.
DISTRO = "\${MCF_DISTRO}"

# If not using mcf, set ROS_DISTRO in conf/bblayers.conf .

# The list of supported values of MACHINE is found in the Machines[] array in the .mcf file for the selected configuration.
# Use ?= so that a value set in the environment will override the one set here.
#MACHINE ?= "<SUPPORTED-MACHINE>"

# Can remove if DISTRO is "webos". If not using mcf, replace \${MCF_OPENEMBEDDED_VERSION} with the version of OpenEmbedded
# being used. See the comments in files/ros*.mcf for its format.
ROS_DISTRO_VERSION_APPEND = "+\${MCF_OPENEMBEDDED_VERSION}"

# Can remove if DISTRO is not "webos". If not using mcf, replace \${MCF_WEBOS_BUILD_NUMBER} with the build number of webOS OSE
# being used.
ROS_WEBOS_DISTRO_VERSION_APPEND = ".\${MCF_WEBOS_BUILD_NUMBER}"

# If not using mcf, replace \${MCF_OE_RELEASE_SERIES} with the OpenEmbedded release series being used.
ROS_OE_RELEASE_SERIES_SUFFIX = "-\${MCF_OE_RELEASE_SERIES}"

# Because of a bug in OpenEmbedded, <ABSOLUTE-PATH-TO-DIRECTORY-ON-SEPARATE-DISK> can not be a symlink.
#ROS_COMMON_ARTIFACTS = "<ABSOLUTE-PATH-TO-DIRECTORY-ON-SEPARATE-DISK>"

# Set the directories where downloads, shared-state, and the output from the build are placed to be on the separate disk.
#DL_DIR = "\${ROS_COMMON_ARTIFACTS}/downloads"
#DL_DIR = "${ARTIFACTS_DIR}/downloads"
SOURCE_MIRROR_URL ?= "file://${ARTIFACTS_DIR}/downloads"
INHERIT += "own-mirrors"
#SSTATE_DIR = "\${ROS_COMMON_ARTIFACTS}/sstate-cache\${ROS_OE_RELEASE_SERIES_SUFFIX}"
#SSTATE_DIR = "${ARTIFACTS_DIR}/${ARTIFACTS_SSTATE_DIR}"
SSTATE_MIRRORS = "file://.* file://${ARTIFACTS_DIR}/${ARTIFACTS_SSTATE_DIR}/PATH"
#TMPDIR = "\${ROS_COMMON_ARTIFACTS}/BUILD-\${DISTRO}-\${ROS_DISTRO}\${ROS_OE_RELEASE_SERIES_SUFFIX}"
# Don't add the libc variant suffix to TMPDIR.
#TCLIBCAPPEND := ""

# As recommended by https://www.yoctoproject.org/docs/latest/mega-manual/mega-manual.html#var-BB_NUMBER_THREADS
# and https://www.yoctoproject.org/docs/latest/mega-manual/mega-manual.html#var-PARALLEL_MAKE:
#BB_NUMBER_THREADS = "\${@min(int(bb.utils.cpu_count()), 20)}"
BB_NUMBER_THREADS = "6"
#PARALLEL_MAKE = "-j \${BB_NUMBER_THREADS}"

# Reduce the size of the build artifacts by removing the working files under TMPDIR/work. Comment this out to preserve them
# (see https://www.yoctoproject.org/docs/latest/mega-manual/mega-manual.html#ref-classes-rm-work).
INHERIT += "rm_work"


# Any other additions to the file go here.

# EXTRA_IMAGE_FEATURES is just one of the many settings that can be placed in this file. You can find them all by searching
# https://www.yoctoproject.org/docs/latest/mega-manual/mega-manual.html#ref-variables-glossary for "local.conf".

# Uncomment to allow "root" to ssh into the device. Not needed for images with webOS OSE because it implicitly adds this
# feature.
# EXTRA_IMAGE_FEATURES += "ssh-server-dropbear"

# Uncomment to include the package management utilities in the image ("opkg", by default). Not needed for images with
# webOS OSE because it implicitly adds this feature.
# EXTRA_IMAGE_FEATURES += "package-management"

# Uncomment to have all interactive shells implicitly source "setup.sh" (ROS 1) or "ros_setup.sh" (ROS 2).
# EXTRA_IMAGE_FEATURES += "ros-implicit-workspace"

# Uncomment to display additional useful variables in the build configuration output.
# require conf/distro/include/ros-useful-buildcfg-vars.inc

# vvvvvvvvvvvvvvvvv In the future, tools will expect to find this line.
# ROS-ADDITIONS-END

#IMAGE_FSTYPES_forcevariable = "tar.gz"
IMAGE_FSTYPES_rpi_pn-webos-image-ros-core = "ota-ext4 wic.bz2"
IMAGE_FSTYPES_rpi_pn-webos-image-ros-world = "ota-ext4 wic.bz2"

# bzip wic.vmdk
IMAGE_FSTYPES_qemux86_pn-webos-image-ros-core = "wic.vmdk.bz2"
IMAGE_FSTYPES_qemux86_pn-webos-image-ros-world = "wic.vmdk.bz2"

ENABLE_UART_rpi = "1"

WEBOS_DISTRO_BUILD_ID = "0"
EOF

if [ "${OE_DISTRO}" != "webos" ] ; then
  cat >> conf/local.conf << EOF
require conf/distro/include/yocto-uninative.inc
INHERIT += "uninative"
EOF
fi

cat >> conf/local.conf << EOF
# To make DL_DIR usable for others as PREMIRROR
BB_GENERATE_MIRROR_TARBALLS = "1"

# We don't use PRservice so git revisions might go backwards
ERROR_QA_remove = "version-going-backwards"
EOF

if [ "${OE_DISTRO}" != "webos" ] ; then
  # These are already enabled in default webOS builds
  cat >> conf/local.conf << EOF
INHERIT += "buildstats"
INHERIT += "buildstats-summary"
INHERIT += "buildhistory"
INHERIT += "image-buildinfo"
EOF
fi

if [ "${OE_DISTRO}" = "webos" ] ; then
  # meta-webosose in default webOS builds has error-report.bbclass modifications
  # to automatically upload results to specified ERR_REPORT_SERVER
  # https://github.com/webosose/meta-webosose/blob/master/meta-webos/classes/report-error.bbclass
  cat >> conf/local.conf << EOF
# Upload the reports to internal error-report-web app
ERR_REPORT_SERVER = "gecko.lge.com"
ERR_REPORT_PORT = "8000"
ERR_REPORT_UPLOAD_ALL = "1"
INHERIT += "report-error"
EOF
fi

echo "Using SSTATE_MIRRORS: `grep ^SSTATE_MIRRORS conf/local.conf`"
echo "Using SOURCE_MIRROR_URL: `grep ^SOURCE_MIRROR_URL conf/local.conf`"

echo "Deleting the TMPDIR"
rm -rf tmp-glibc

# for r in asio boost cmake cppcheck curl elfutils ffmpeg freeglut freetype gcc git gnupg gpgme joystick libeigen libjpeg-turbo libogg libpcre libtinyxml2 libusb1 libx11 libxml2 linux-raspberrypi linux-yocto lz4 mesa opencv openssl pkgconfig poco python-future python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-pyyaml python3-setuptools qtbase sqlite3 systemd udev yaml-cpp; do
#for r in ffmpeg freeglut linux-raspberrypi linux-yocto mesa pkgconfig python-future python-lxml python-numpy python-sip python3-lxml python3-pyqt5 qtbase udev; do
for r in ffmpeg freeglut linux-raspberrypi linux-yocto mesa pkgconfig python-future python-lxml python-numpy python-sip python3-lxml python3-pyqt5 qtbase udev; do
    for MACHINE in ${MACHINES}; do
        bitbake -e ${r} | tee env.versions.${r}.${MACHINE}
    done
done

echo "Deleting the TMPDIR"
rm -rf tmp-glibc

echo "sstate reuse stats:"
grep "^NOTE: .* sstate reuse.*scratch" log.*.${BUILD_DATE}.$$ | sort | uniq -c

echo "WARNING messages about generic license:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "No generic license file exists for: " | sort | uniq -c

echo "WARNING messages about license:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "The license listed .* was not in the licenses collected for recipe" | sort | uniq -c

echo "WARNING messages about version going backwards:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "\[version-going-backwards\]" | sort | uniq -c

echo "WARNING messages caused by sota:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "Android repo tool not found" | sort | uniq -c
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "Data in /media directory is not preserved by OSTree" | sort | uniq -c
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "SOTA_PACKED_CREDENTIALS not set." | sort | uniq -c

echo "Other WARNING messages:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep -v "No generic license file exists for: " | grep -v "The license listed .* was not in the licenses collected for recipe" | grep -v "Android repo tool not found" | grep -v "Data in /media directory is not preserved by OSTree" | grep -v "SOTA_PACKED_CREDENTIALS not set." | grep -v "\[version-going-backwards\]" | sort | uniq -c

echo "ERROR messages:"
grep "^ERROR:" log.*.${BUILD_DATE}.$$ | sort | uniq -c

echo "Other error messages:"
grep -i "error[: ]" log.*.${BUILD_DATE}.$$ | grep -v "ERROR:" | grep -v "Summary:" | sort | uniq -c

echo "Summary:"
grep "Summary:" log.*.${BUILD_DATE}.$$ | sort | uniq -c

exit ${RESULT}

# for i in asio boost cmake cppcheck curl elfutils ffmpeg freeglut freetype gcc git gnupg gpgme joystick libeigen libjpeg-turbo libogg libpcre libtinyxml2 libusb1 libx11 libxml2 linux-raspberrypi linux-yocto lz4 mesa opencv openssl pkgconfig poco python-future python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-pyyaml python3-setuptools qtbase sqlite3 systemd udev yaml-cpp; do grep /${i}_.*.do_populate_lic *dashing*/log.world-all.* | sed 's#/log.world-all.*/\([^_]*\)_# ---- \1 ---- #g; s#.bb:do_.*##g' | sort -u; done | tee log.versions

# for i in ros2-dashing-dunfell/ ros2-dashing-gatesgarth/ ros2-dashing-hardknott/ webos-dashing-dunfell/ webos-dashing-gatesgarth/ webos-dashing-hardknott/ ros1-melodic-gatesgarth/ ros1-melodic-hardknott/ ros1-melodic-dunfell/; do ./build-one-version.sh $i; done

# for r in ffmpeg freeglut linux-raspberrypi linux-yocto mesa pkgconfig python-future python-lxml python-numpy python-sip python3-lxml python3-pyqt5 qtbase udev; do
#     grep ^PV= */env.versions.${r}*;
# done

# E: Unable to locate package asio
# libasio-dev
# E: Unable to locate package boost
# libboost-all-dev
# E: Unable to locate package freeglut
# freeglut3-dev
# E: Unable to locate package freetype
# libfreetype6
# E: Unable to locate package gpgme
# libgpgme-dev
# E: Unable to locate package libeigen
# libeigen3-dev
# E: Unable to locate package libjpeg-turbo
# libjpeg-dev
# E: Unable to locate package libogg
# libogg-dev
# E: Unable to locate package libpcre
# libpcre3-dev
# E: Unable to locate package libtinyxml2
# libtinyxml-dev
# E: Unable to locate package libusb1
# libusb-1.0-0
# E: Unable to locate package libx11
# libx11-dev
# E: Unable to locate package linux-raspberrypi
# E: Unable to locate package linux-yocto
# E: Package 'lz4' has no installation candidate
# liblz4-dev
# E: Unable to locate package mesa
# libgl1-mesa-dev
# E: Unable to locate package opencv
# libopencv-dev
# E: Unable to locate package pkgconfig
# pkg-config
# E: Unable to locate package poco
# libpoco-dev
# E: Unable to locate package python3-pyyaml
# python3-yaml
# E: Unable to locate package qtbase
# libqt5core5a
# E: Unable to locate package yaml-cpp
# libyaml-cpp-dev
# apt install libasio-dev libboost-all-dev freeglut3-dev libfreetype6 libgpgme-dev libeigen3-dev libjpeg-dev libogg-dev libpcre3-dev libtinyxml-dev libusb-1.0-0 libx11-dev liblz4-dev libgl1-mesa-dev libopencv-dev pkg-config libpoco-dev python3-yaml libqt5core5a libyaml-cpp-dev | tee apt.install1

# for i in  libasio-dev libboost-all-dev freeglut3-dev libfreetype6 libgpgme-dev libeigen3-dev libjpeg-dev libogg-dev libpcre3-dev libtinyxml-dev libusb-1.0-0 libx11-dev liblz4-dev libgl1-mesa-dev libopencv-dev pkg-config libpoco-dev python3-yaml libqt5core5a libyaml-cpp-dev; do grep "^Setting.*$i " apt.install1; done

# apt install cmake cppcheck curl elfutils ffmpeg gcc git gnupg joystick libxml2 openssl python-futures python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-setuptools sqlite3 systemd udev | tee apt.install2

# for i in cmake cppcheck curl elfutils ffmpeg gcc git gnupg joystick libxml2 openssl python-futures python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-setuptools sqlite3 systemd udev; do grep "^Setting.*$i " apt.install2; done

# apt install libasio-dev boost cmake cppcheck curl elfutils ffmpeg freeglut freetype gcc git gnupg gpgme joystick libeigen libjpeg-turbo libogg libpcre libtinyxml2 libusb1 libx11 libxml2 linux-raspberrypi linux-yocto lz4 mesa opencv openssl pkgconfig poco python-futures python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-pyyaml python3-setuptools qtbase sqlite3 systemd udev yaml-cpp | tee apt.install3

# for i in  libasio-dev libboost-all-dev freeglut3-dev libfreetype6 libgpgme-dev libeigen3-dev libjpeg-dev libogg-dev libpcre3-dev libtinyxml-dev libusb-1.0-0 libx11-dev liblz4-dev libgl1-mesa-dev libopencv-dev pkg-config libpoco-dev python3-yaml libqt5core5a libyaml-cpp-dev cmake cppcheck curl elfutils ffmpeg gcc git gnupg joystick libxml2 openssl python-futures python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-setuptools sqlite3 systemd udev; do dpkg -l $i; done | grep ^ii
