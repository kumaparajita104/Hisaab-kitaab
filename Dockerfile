FROM eclipse-temurin:17

LABEL mentainer="kumaparajita1@gmail.com"

WORKDIR /app

COPY target/Hissab-Kitaab-0.0.1-SNAPSHOT.jar /app/hissab-kitaab-docker.jar

ENTRYPOINT ["java","-jar","hissab-kitaab-docker.jar"]