#!/bin/bash

DIST_PATH="$(pwd)/../dist"
TARGET_PATH="$(pwd)/../dist"
HELPER_PATH="$(pwd)/helper"

# =============== Prepare service structure ===============
echo "Prepare directory struct"
if [ -d $DIST_PATH ] || [ -f $(pwd)/backend/Dockerfile ]; then
    rm -rf $DIST_PATH;
    rm -rf $TARGET_PATH;
    rm -rf config_backend.env;
    rm -rf config_db.env;
    rm -rf config_pgadmin.env;
    rm -rf docker-compose.yml;
    rm -rf backend/target;
    echo "Catalog structure rebuilding"
fi

if [ $# = 0 ]; then
  mkdir -p $DIST_PATH/nginx;
  mkdir -p $DIST_PATH/flyway_config;
  mkdir -p $DIST_PATH/flyway_migration;
  mkdir -p $DIST_PATH/import;
  mkdir -p $DIST_PATH/pgadmin/storage/root_root.com/;
  echo "Created dist directory structure"

  cp -r $HELPER_PATH/data_for_import/ $DIST_PATH/import;
  cp -r $HELPER_PATH/flyway_migration/ $DIST_PATH/flyway_migration;
  cp -r $HELPER_PATH/pgadmin_config/* $DIST_PATH/pgadmin/storage/root_root.com;
  cp -r $HELPER_PATH/flyway_config/ $DIST_PATH/flyway_config;
  cp -r $HELPER_PATH/nginx/ $DIST_PATH/nginx;
  cp -r $HELPER_PATH/configs/ $(pwd);
  cp -r $HELPER_PATH/docker/docker-compose.yml $(pwd);
  echo "Copy file for launch"

  docker-compose down;
  docker-compose up --build
fi
