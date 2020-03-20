#!/bin/bash

SCRIPTDIR=$(dirname $(readlink --canonicalize $0))

source ${SCRIPTDIR}/build-part-update.sh

source ${SCRIPTDIR}/build-part-configure.sh

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

source ${SCRIPTDIR}/build-part-bitbake.sh

[ -z "${RM_WORK_DISABLED}" ] && echo "Deleting the TMPDIR" && rm -rf tmp-glibc

source ${SCRIPTDIR}/build-part-results.sh

exit ${RESULT}
