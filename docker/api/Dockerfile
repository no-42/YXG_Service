FROM openjdk:11
LABEL org.opencontainers.image.authors="thetbw@outlook.com"
RUN useradd -d /home/ruoyi -m -s /bin/bash ruoyi 
ADD ruoyi-api.jar /home/ruoyi/ruoyi-api.jar
RUN mkdir -p "/home/ruoyi/data/logs"
RUN chown -R ruoyi:ruoyi "/home/ruoyi/data"
RUN chown ruoyi:ruoyi /home/ruoyi/ruoyi-api.jar
VOLUME /home/ruoyi/data
USER ruoyi
WORKDIR /home/ruoyi
ENV TZ=Asia/Shanghai
EXPOSE 8081
#服务器健康检查
HEALTHCHECK --interval=1m --timeout=3s CMD curl -f http://localhost:8081 || exit 1
ENTRYPOINT ["java","-jar","ruoyi-api.jar"]
