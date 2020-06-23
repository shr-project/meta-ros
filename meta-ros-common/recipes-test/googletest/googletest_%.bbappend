# Copyright (c) 2020 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += "file://0001-fuse_gmock_files.py-update-for-python3-compatibility.patch"

# GTest developers recommend to use source code instead of linking
# against a prebuilt library.
do_install_append() {
    rm -rf ${B}/fused-src || true
    mkdir ${B}/fused-src || true
    ${S}/googletest/scripts/fuse_gtest_files.py ${B}/fused-src
    cp -vf ${S}/googletest/src/gtest_main.cc ${B}/fused-src/gtest/

    install -d ${D}${prefix}/src/gtest/src
    install -d ${D}${prefix}/src/gtest/cmake

    install -v -m 0644 ${B}/fused-src/gtest/* ${D}${prefix}/src/gtest/src
    install -v -m 0644 ${S}/googletest/CMakeLists.txt ${D}${prefix}/src/gtest
    install -v -m 0644 ${S}/googletest/cmake/* ${D}${prefix}/src/gtest/cmake
    install -v -m 0644 ${S}/googletest/cmake/gtest.pc.in ${D}${prefix}/src/gtest
    
    install -d ${D}${bindir}/gtest
    install -v -m 0755 ${S}/googletest/test/*.py ${D}${bindir}/gtest

    # do the same for gmock
    rm -rf ${B}/fused-src-gmock || true
    mkdir ${B}/fused-src-gmock || true
    ${S}/googlemock/scripts/fuse_gmock_files.py ${B}/fused-src-gmock
    cp -vf ${S}/googlemock/src/gmock_main.cc ${B}/fused-src-gmock/gmock/

    install -d ${D}${prefix}/src/gmock/src
    install -d ${D}${prefix}/src/gmock/cmake

    install -v -m 0644 ${B}/fused-src-gmock/gmock/* ${D}${prefix}/src/gmock/src
    install -v -m 0644 ${S}/googlemock/CMakeLists.txt ${D}${prefix}/src/gmock
    install -v -m 0644 ${S}/googlemock/cmake/* ${D}${prefix}/src/gmock/cmake
    install -v -m 0644 ${S}/googlemock/cmake/gmock.pc.in ${D}${prefix}/src/gmock

    install -d ${D}${bindir}/gmock
    install -v -m 0755 ${S}/googlemock/test/*.py ${D}${bindir}/gmock

    # install these 2 as well, because /usr/share/ament_cmake_gmock/cmake/ament_cmake_gmock-extras.cmake
    # expects to find gmock-all.cc (the gmock-all.cc in ${S}/googlemock/src/gmock-all.cc isn't fused)
    install -v -m 0644 ${B}/fused-src-gmock/gmock-gtest-all.cc ${D}${prefix}/src/gmock/src
    ln -snf gmock-gtest-all.cc ${D}${prefix}/src/gmock/src/gmock-all.cc

    # add_subdirectory given source "../googletest" which is not an existing directory.
    sed -i '/add_subdirectory(".{gtest_dir}" ".{gmock_BINARY_DIR}..{gtest_dir}")/d' ${D}${prefix}/src/gmock/CMakeLists.txt
}

sysroot_stage_all_append() {
    sysroot_stage_dir ${D}${prefix}/src ${SYSROOT_DESTDIR}${prefix}/src
}

FILES_${PN} += "${bindir}/gtest ${bindir}/gmock"
FILES_${PN}-dev += "${prefix}/src"
