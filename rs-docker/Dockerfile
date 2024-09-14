# 使用官方OpenJDK 21基础镜像
FROM openjdk:21-jdk

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 将项目的jar包复制到工作目录
COPY loginDemo-0.0.1-SNAPSHOT.jar /app.jar

# 暴露容器的端口
EXPOSE 9020

# 运行jar包
CMD ["java", "-jar", "app.jar"]
