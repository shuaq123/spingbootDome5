# 使用基础镜像作为构建起点
FROM ubuntu:latest
# 设置基础镜像
#RUN apt-get update && apt-get install -y wget
#RUN wget https://download.java.net/java/GA/jdk17/0d48333b42344c269b66252508d49c46/jdk-17.0.1_osx-amd64_bin.tar.gz -R && \
#       tar -xzf jdk-17.0.1_osx-amd64_bin.tar.gz && \
#       mv jdk-17.0.1_osx-amd64_bin jdk17 && \
#       rm jdk-17.0.1_osx-amd64_bin.tar.gz
# 将本地的 JDK 复制到镜像中
COPY ./jdk-17.0.1 /jdk-17.0.1
# 设置环境变量 JAVA_HOME
ENV JAVA_HOME=/jdk-17.0.1
ENV PATH=$PATH:$JAVA_HOME/bin

COPY target/demo4-0.0.1-SNAPSHOT.jar /demo4-0.0.1-SNAPSHOT.jar
# 设置工作目录
WORKDIR /
# 暴露 Spring Boot 项目的端口号
EXPOSE 8214
# 执行 Spring Boot 应用
CMD ["java", "-jar", "/demo4-0.0.1-SNAPSHOT.jar"]