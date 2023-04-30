FROM amazoncorretto:11-alpine-jdk

MAINTAINER AntoMontagna

COPY target/Backend-0.0.1-SNAPSHOT app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
