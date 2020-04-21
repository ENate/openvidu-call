#!/bin/bash

[[ -z "${OPENVIDU_URL}" ]] && export OPENVIDU_URL=$(curl -s ifconfig.co)
[[ -z "${OPENVIDU_SECRET}" ]] && export OPENVIDU_SECRET=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)

echo "OPENVIDU URL: ${OPENVIDU_URL}"
echo "OPENVIDU SECRET: ${OPENVIDU_SECRET}"
echo "OPENVIDU CALL SERVER PORT: ${SERVER_PORT}"

OPENVIDU_URL=${OPENVIDU_URL} OPENVIDU_SECRET=${OPENVIDU_SECRET} SERVER_PORT=${SERVER_PORT} node /opt/openvidu-call/dist/app.js