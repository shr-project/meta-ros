#!/bin/bash

SCRIPTDIR=$(dirname $(readlink --canonicalize $0))

# One machine is enough
MACHINE="raspberrypi4" && MACHINES="${MACHINE}"

source ${SCRIPTDIR}/build-part-update.sh

RM_WORK_DISABLED="true"
cd openembedded-core
git chp db1bada8cefb9d95df9a32c446b95dd303524a0d
cd ..

source ${SCRIPTDIR}/build-part-configure.sh

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

source ${SCRIPTDIR}/build-part-bitbake.sh

echo "Moving TMPDIR to tmp-glibc.${BUILD_DATE}" && mv tmp-glibc tmp-glibc.${BUILD_DATE}
echo "Copying buildhistory to buildhistory.${BUILD_DATE}" && cp -ra buildhistory buildhistory.${BUILD_DATE}

source ${SCRIPTDIR}/build-part-results.sh

exit ${RESULT}
