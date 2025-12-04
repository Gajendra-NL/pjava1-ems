From eclipse-temurin:21

LABEL maintainer="gcloud.test@gmail.com"

WORKDIR /app

COPY target/p1ems-backend-0.0.1-SNAPSHOT.jar /app/spring-p1ems-backend.jar

ENTRYPOINT ["java", "-jar", "spring-p1ems-backend.jar"]