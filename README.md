#invoke-aws-lambda-ioc-docker
invoke aws lambda using springboot api with inversion of control (Dependency Injection) using Docker images in docker container


#Maven Commands
mvn clean install

mvn clean package docker:build

#Docker Commands
docker login {optional url}

docker push akshay3030/invoke-lambda

docker pull akshay3030/invoke-lambda


docker run -e AWS_CREDENTIAL_PROFILES_FILE=/home/swansh/.aws/credentials -v /home/swansh/.aws:/home/swansh/.aws -p 9000:9090 -e spring.profiles.active=local akshay3030/invoke-lambda

############################################################
# Additional Notes with More docker commands

mvn clean package docker:build

#mvn package build image

docker login {docker hub url}

docker push { Full Repository Name }

docker pull { Full Repository Name }

docker run {image name or image id}

docker images

docker tag b5365604e06b {repository name or image id}

# Stop Docker Container
docker ps

docker stop {psid}

#Run with aws tokens from host machine:
docker run -p 9095:9090 -e spring.profiles.active="local" {image name or image id}

#Run with was tokens from docker image:
docker run -e AWS_CREDENTIAL_PROFILES_FILE=<path>/.aws/credentials -v <path>/.aws:<path>/.aws -p 9095:9090 -e spring.profiles.active="local" {image name or image id}

http://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html


#To login to docker image, if it has bash already :

docker run -i -t {image name or image id}

docker run -it {image name or image id} /bin/bash

docker run -d {image name or image id} /bin/bash

#ECS-Sample-App
docker tag 56dc566c8e35 {tag-name}

docker run -i {image name or image id}

docker run -i -p 8087:80 {image name or image id}

#Docker Logs:
docker logs --details { psid }

#Docker Name: Give a custom name to image
docker run --name akshay { image name }

#Build Image from local, run below at root of Dockerfile:
docker build -t {image name} .

# Remove commands
docker rm :remove container

docker rmi :remove images




