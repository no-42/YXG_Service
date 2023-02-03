FROM openjdk:11
LABEL org.opencontainers.image.authors="thetbw@outlook.com"
RUN useradd -d /home/ruoyi -m -s /bin/bash ruoyi 
ADD ruoyi-admin.jar /home/ruoyi/ruoyi-admin.jar
RUN mkdir -p "/home/ruoyi/data/logs"
RUN chown -R ruoyi:ruoyi "/home/ruoyi/data"
RUN chown ruoyi:ruoyi /home/ruoyi/ruoyi-admin.jar
VOLUME /home/ruoyi/data
USER ruoyi
WORKDIR /home/ruoyi
ENV TZ=Asia/Shanghai
EXPOSE 8080
#服务器健康检查
HEALTHCHECK --interval=1m --timeout=3s CMD curl -f http://localhost:8080/api || exit 1
ENTRYPOINT ["java","-jar","ruoyi-admin.jar"]
