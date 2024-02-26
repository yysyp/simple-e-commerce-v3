#! /bin/bash
set -eu

. ./0setenv.sh
appversion=$(date +"%Y%m%d-%H%M%S")
CURDIR=$(pwd)

cd ..
docker build -t simple-e-commerce-v3:${appversion} -f Dockerfile .
#gcloud auth configure-docker xxx.com
docker tag simple-e-commerce-v3:${appversion} xxx.com/path/repo/simple-e-commerce-v3:${appversion}

docker push xxx.com/path/repo/simple-e-commerce-v3:${appversion}

cd "${CURDIR}"
cp deploy-template.yaml deploy.yaml
sed -i "s/THE_APP_VERSION/${appversion}/g" deploy.yaml

