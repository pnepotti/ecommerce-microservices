# First stage: Build the application
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

COPY . .

RUN mvn clean package -DskipTests -pl microservices/product-microservice -am

# Second stage: Run the application
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

RUN apk add --no-cache curl bash

COPY --from=build /app/microservices/product-microservice/target/*.jar app.jar

EXPOSE 8084

ENTRYPOINT [ "java", "-jar", "app.jar" ]
