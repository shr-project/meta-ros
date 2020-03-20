#!/bin/bash

export MCFFILES="meta-ros-build/files/*.mcf meta-ros-build/files-contrib/*.mcf"

for i in ${MCFFILES}; do
    MCF=`basename ${i/.mcf/}`
    meta-ros-build/build-job-local-quick-build-test.sh ${MCF} | tee log.${MCF}
done
