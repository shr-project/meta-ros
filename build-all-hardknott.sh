#!/bin/bash

export MCFFILES="meta-ros-build/files*/*hardknott.mcf"

for i in ${MCFFILES}; do
    ID=`basename ${i/.mcf/}`
    ./build-one.sh $ID
done
