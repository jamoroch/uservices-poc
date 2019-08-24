# uServices-poc
Two Microservices (hermes and apollo) to test the effect schedlock across of a multiple instances of apollo

## Build Hermes
mvn package
docker build -f docker/Dockerfile -t hermes .
 docker run -p 1000:1000 -d hermes --name hermes_1
