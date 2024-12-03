FROM openjdk:17

WORKDIR /app

COPY ./target/dashboard.jar /app

EXPOSE 8080

CMD ["java", "-jar","dashboard.jar"]