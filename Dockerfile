FROM gradle:jdk11 AS build
COPY  . /gradle
WORKDIR /gradle
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /gradle/build/libs/*.jar /app
WORKDIR /app

CMD ["java","-jar","todoservice.jar"]