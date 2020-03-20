#!/bin/bash

SCRIPTDIR=$(dirname $(readlink --canonicalize $0))

source ${SCRIPTDIR}/build-part-update.sh

source ${SCRIPTDIR}/build-part-configure.sh

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

RECIPES="asio boost cmake cppcheck curl elfutils ffmpeg freeglut freetype gcc git gnupg gpgme joystick libeigen libjpeg-turbo libogg libpcre libtinyxml2 libusb1 libx11 libxml2 linux-raspberrypi linux-yocto lz4 mesa opencv openssl pkgconfig poco python-future python-lxml python-numpy python-sip python3 python-lxml python3-numpy python3-psutil python3-pydot python3-pygraphviz python3-pyqt5 python3-pytest python3-pyyaml python3-setuptools qtbase sqlite3 systemd udev yaml-cpp"
for r in ${RECIPES}; do
#for r in ffmpeg freeglut linux-raspberrypi linux-yocto mesa pkgconfig python-future python-lxml python-numpy python-sip python3-lxml python3-pyqt5 qtbase udev; do
    for MACHINE in ${MACHINES}; do
        bitbake -e ${r} 2>&1 | tee env.versions.${r}.${MACHINE}
    done
done

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

source ${SCRIPTDIR}/build-part-results.sh

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

exit 0

for m in qemu raspberrypi; do
  echo -ne "name\t" > versions.${m}.csv
  for r in dunfell gatesgarth hardknott honister; do
    echo -ne "$r\t" >> versions.${m}.csv
  done
  echo >> versions.${m}.csv
  for c in ${RECIPES}; do
    echo -ne "$c\t";
    for r in dunfell gatesgarth hardknott honister; do
      r_v=`grep ^PV= ros*$r/env.versions.${c}.${m}* | sed 's/^PV="//g; s/"$//g'`
      w_v=`grep ^PV= webos*$r/env.versions.${c}.${m}* | sed 's/^PV="//g; s/"$//g'`
      echo -ne "$r_v"
      if [ "${r_v}" != "${w_v}" ]; then
        echo -ne "($w_v)"
      fi
      echo -ne "\t"
    done
    echo
  done >> versions.${m}.csv
done
