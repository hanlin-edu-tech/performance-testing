#! bash

cd quarkus
./gradlew build
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.jvm -t performance-testing-quarkus-jvm .
docker build -f src/main/docker/Dockerfile.native -t performance-testing-quarkus-native .

cd ../nodejs-express
nvm use
npm install
docker build -f dockerfile -t performance-testing-nodejs-express .

cd ../nodejs-fastify
nvm use
npm install
docker build -f dockerfile -t performance-testing-nodejs-fastify .