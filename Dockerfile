
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/java-ci-cd-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xms32m", "-Xmx128m", "-jar", "app.jar"]
