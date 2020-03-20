#!/bin/bash

export MCFFILES="meta-ros-build/files*/*dunfell.mcf"

for i in ${MCFFILES}; do
    ID=`basename ${i/.mcf/}`
    if echo $ID | grep -q eloquent; then echo "INFO: skipping EOL eloquent"; continue; fi
    if echo $ID | grep -q dashing; then echo "INFO: skipping EOL dashing"; continue; fi
    if echo $ID | grep -q gatesgarth; then echo "INFO: skipping EOL gatesgarth"; continue; fi
    meta-ros-build/build-job-local.sh $ID
done
