FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=prod
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src
RUN ./gradlew clean build -x test
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/mylibrary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/mylibrary.jar"]