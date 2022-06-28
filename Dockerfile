FROM openjdk:11
WORKDIR /usr/src/app
COPY . .
RUN ./gradlew shadowJar --no-daemon
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/BFF-ktor-0.0.1-all.jar"]