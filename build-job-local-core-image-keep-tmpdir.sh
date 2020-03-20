#!/bin/bash

SCRIPTDIR=$(dirname $(readlink --canonicalize $0))

export IMAGES="core"

source ${SCRIPTDIR}/build-part-update.sh

RM_WORK_DISABLED="true"

source ${SCRIPTDIR}/build-part-configure.sh

source ${SCRIPTDIR}/build-part-bitbake.sh

source ${SCRIPTDIR}/build-part-results.sh

exit ${RESULT}
