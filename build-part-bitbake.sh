# no shebang, source this from other script

for MACHINE in ${MACHINES}; do
    for I in ${IMAGES}; do
        IM="ros-image-${I}"
        [ "${OE_DISTRO}" = "webos" ] && IM="webos-image-ros-${I}"
        bitbake ${BITBAKE_PARAMS} -k $IM 2>&1 | tee log.image-$I.$MACHINE.${BUILD_DATE}.$$
	RESULT+=${PIPESTATUS[0]}
    done
    for T in ${TARGETS}; do
        bitbake ${BITBAKE_PARAMS} -k $T 2>&1 | tee log.$T.$MACHINE.${BUILD_DATE}.$$
        RESULT+=${PIPESTATUS[0]}
    done
done
