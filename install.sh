#! bash

cd quarkus
./gradlew build
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.jvm -t performance-testing-quarkus-jvm .
docker build -f src/main/docker/Dockerfile.native -t performance-testing-quarkus-native .

cd ../nodejs-express
docker build -f dockerfile -t performance-testing-nodejs-express .

cd ../nodejs-fastify
docker build -f dockerfile -t performance-testing-nodejs-fastify .