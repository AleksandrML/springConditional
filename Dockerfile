FROM openjdk:18-jdk-alpine
EXPOSE 8081
ADD build/libs/springConditional-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
