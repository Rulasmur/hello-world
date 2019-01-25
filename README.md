# Build
mvn package
# Docker Build
docker build -t rulasmur/hello-world .
# Docker Run
docker run -p 8180:8080 rulasmur/hello-world
