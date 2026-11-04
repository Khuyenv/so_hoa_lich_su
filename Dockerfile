# Dockerfile cho ứng dụng Số hóa Luật tục & Hương ước làng xã (Mã C05)
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Copy mã nguồn và Maven Wrapper
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Tạo user không có quyền root để tăng bảo mật
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
