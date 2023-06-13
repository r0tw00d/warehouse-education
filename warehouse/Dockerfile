FROM openjdk:17-alpine
LABEL maintainer="Alex Astrologov"
VOLUME /warehouse
ADD target/warehouse-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","/app.jar"]