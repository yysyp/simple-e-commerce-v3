#! /bin/bash
set -eu

. ./0setenv.sh

cd ../../

docker build -t simple-e-commerce-v3:${appversion} -f Dockerfile .
#echo 'password123' | docker login --username user123 --password-stdin https://xxx.com:12345
#docker tag simple-e-commerce-v3:${appversion} xxx.com/path/repo/simple-e-commerce-v3:${appversion}
#docker push xxx.com/path/repo/simple-e-commerce-v3:${appversion}

docker run --name simple-e-commerce-v3 --rm -itd --add-host=host.docker.internal:host-gateway -p 8080:8080 simple-e-commerce-v3:${appversion}
