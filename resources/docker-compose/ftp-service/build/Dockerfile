# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD ftp-0.0.1-SNAPSHOT.jar /ftp-0.0.1-SNAPSHOT.jar
# 运行过程中创建一个ftp-0.0.1-SNAPSHOT.jar文件
RUN bash -c 'touch /ftp-0.0.1-SNAPSHOT.jar'
# 声明服务运行在8080端口
EXPOSE 8071
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/ftp-0.0.1-SNAPSHOT.jar"]
# 指定维护者的名字
MAINTAINER wanghuan
