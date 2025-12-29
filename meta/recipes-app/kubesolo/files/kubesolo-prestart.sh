#!/bin/bash

# Validation script for kubesolo service
# Checks if required environment variables are set

ENV_FILE="/var/lib/kubesolo/config"

if [ ! -f "$ENV_FILE" ]; then
    echo "Configuration file $ENV_FILE does not exist"
    exit 1
fi

# Source the environment file
source "$ENV_FILE"

# Check if required variables are set and not empty
if [ -z "$EDGE_ID" ]; then
    echo "ERROR: EDGE_ID is not set in $ENV_FILE"
    exit 1
fi

if [ -z "$EDGE_PORTAINER_KEY" ]; then
    echo "ERROR: EDGE_PORTAINER_KEY is not set in $ENV_FILE"
    exit 1
fi

echo "Configuration validated successfully"
exit 0
