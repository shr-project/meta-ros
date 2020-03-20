#!/bin/bash

export MCFFILES="meta-ros-build/files*/webos*honister.mcf"

for i in ${MCFFILES}; do
    ID=`basename ${i/.mcf/}`
    meta-ros-build/build-job-local-bh.sh $ID
done
