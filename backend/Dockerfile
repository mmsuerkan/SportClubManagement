FROM openjdk:11
MAINTAINER mmerts
VOLUME /tmp
EXPOSE 8080
ADD target/sport-club-management-docker.jar sport-club-management-docker.jar
ENTRYPOINT ["-jar","/sport-club-management-docker.jar"]
