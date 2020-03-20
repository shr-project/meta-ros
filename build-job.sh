#!/bin/bash

SCRIPTDIR=$(dirname $(readlink --canonicalize $0))

source ${SCRIPTDIR}/build-part-update.sh

NFS_IS_RW="true"

source ${SCRIPTDIR}/build-part-configure.sh

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

source ${SCRIPTDIR}/build-part-bitbake.sh

if [ "${JOB_TYPE}" = "world" ] ; then
    echo "Nothing to store on fileserver from world builds"
else
    echo "Storing the deploy/images dir on fileserver"
    mkdir -p /mnt/mirror-write-artifacts/${JOB_NAME}/${BUILD_NUMBER}

    if [ "${OE_DISTRO}" = "webos" ] ; then
        # Remove big useless intermediate image used by sota
        rm -rf tmp-glibc/deploy/images/raspberrypi*/*.rootfs.ota-ext4
        # Remove symlinks
        find tmp-glibc/deploy/images/* -type l -delete
        rsync --stats -r tmp-glibc/deploy/images/* /mnt/mirror-write-artifacts/${JOB_NAME}/${BUILD_NUMBER}
    else
        # Remove symlinks
        find tmp-glibc/deploy/images/* -type l -delete
        # Rsync only raspberrypi images, qemux86 images are usually tested with runqemu on build server
        rsync --stats -r tmp-glibc/deploy/images/raspberrypi* /mnt/mirror-write-artifacts/${JOB_NAME}/${BUILD_NUMBER}
    fi
fi

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

source ${SCRIPTDIR}/build-part-results.sh

exit ${RESULT}
