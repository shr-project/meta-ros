DESCRIPTION = "Python interface to PROJ.4 library"
SECTION = "devel/python"
LICENSE = "ISC & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77d9726a341183ab262b28b3d66dfd94"

inherit pypi

SRC_URI[md5sum] = "c845c553bb903e6f13f61fbcc8291652"
SRC_URI[sha256sum] = "2821f7fdfe4d2c5916e71f80d0f4029d82785f477a11223e70de2db39a1f26dd"

inherit setuptools3

DEPENDS += "\
    ${PYTHON_PN}-cython-native \
    proj-native \
"
