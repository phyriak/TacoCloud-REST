FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_DATASOURCE_USERNAME taco
ENV SPRING_DATASOURCE_PASSWORD taco
ENV SPRING_PROFILES_ACTIVE test
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]