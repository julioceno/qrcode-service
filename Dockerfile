FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY /target/qrcodeservice-1.0.0.jar app.jar


EXPOSE 8080
CMD ["java","-jar","app.jar"]