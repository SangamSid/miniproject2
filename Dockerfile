FROM openjdk:17
MAINTAINER sangam sangam142@gmail.com
COPY target/project2-0.0.1-SNAPSHOT.jar /usr/app
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "project2-0.0.1-SNAPSHOT.jar"]