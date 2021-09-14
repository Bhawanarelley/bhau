#
# Dockerfile for Streaming generic base image
#
# Copyright (c) 2018-2020 TIBCO Software Inc.
#
FROM centos:8

LABEL description="TIBCO Streaming base image"
LABEL PRODUCT_VERSION=${docker_base_version}

#
# Set environment
#
ENV STREAMING_PRODUCT_HOME     /opt/tibco/streambase
ENV STREAMING_RUNTIME_HOME     /var/opt/tibco/streambase
ENV JAVA_HOME        /etc/alternatives/jre
ENV PATH             /bin:/usr/sbin:${STREAMING_PRODUCT_HOME}/distrib/tibco/bin
ENV USER_NAME        tibco

#
# Add required additional packages
#
# systat    required for runtime statistcs
# gdb       required for crash dumps
# java      required for applications
# zip       required for snapshots
# unzip     required to unpack runtime on windows
# libnsl    required by epadmin
# less      to aid log file reading
#
# FIX THIS:
#	java installed separately due to https://access.redhat.com/solutions/1277233
#	java pending update to 11
#
RUN yum --assumeyes --exclude=gcc --exclude=binutils install \
    sysstat \
    gdb \
    zip \
    unzip \
    libnsl \
    less \
    && yum --assumeyes install java-11-openjdk-headless \
    && yum clean all

#
# Create a user to run nodes
#
RUN /usr/sbin/useradd \
    --comment "TIBCO Streaming User" \
    --create-home \
    --user-group \
    --password ${USER_NAME} \
    --shell /bin/bash \
    ${USER_NAME}

#
# Install runtime
#
RUN mkdir -p ${STREAMING_PRODUCT_HOME}
COPY maven ${STREAMING_PRODUCT_HOME}
RUN chmod a+x ${STREAMING_PRODUCT_HOME}/start-node
RUN ${docker.run.unpack}

#
# Allow user access to nodes and applications
#
RUN mkdir -p ${STREAMING_RUNTIME_HOME}
RUN chown ${USER_NAME}:${USER_NAME} ${STREAMING_RUNTIME_HOME}

#
# Change to guest user
#
USER ${USER_NAME}
