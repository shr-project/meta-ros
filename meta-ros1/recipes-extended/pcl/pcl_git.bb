require recipes-extended/pcl/pcl.inc

PV = "1.9.1+git${SRCPV}"
SRCREV = "72f41b60a539cd1da67d1329b57222290122a0bb"
SRC_URI += "file://0001-Fix-deprecated-boost-endians.patch"

# The build is really memory hungry (at least with gcc8), even with just -j 8 it triggers OOMK on system with 32GB ram
# High memory needs mentioned in: https://github.com/PointCloudLibrary/pcl/issues/2284
# Setting just empty doesn't work, ninja will by default use number of cores available
PARALLEL_MAKE = "-j4"
