# 使用轻量级的 JDK 21 运行环境
FROM eclipse-temurin:21-jr-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]