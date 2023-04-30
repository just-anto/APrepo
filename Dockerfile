FROM amazoncorretto:11-alpine-jdk

MAINTAINER AntoMontagna

COPY target/Backend-0.0.1-SNAPSHOT back-app.jar

ENTRYPOINT ["java","-jar","/back-app.jar"]
