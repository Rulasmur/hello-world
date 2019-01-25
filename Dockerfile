FROM openjdk:8
MAINTAINER Rulasmur
COPY target/hello-world.jar /opt/hello-world/
#COPY files/spring-cloud-config-server-entrypoint.sh /opt/spring-cloud/bin/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/hello-world/hello-world.jar"]
EXPOSE 8080