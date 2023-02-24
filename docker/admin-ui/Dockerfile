FROM lipanski/docker-static-website:latest
# 使用 .dockerignore 文件来控制镜像中的内容！
# 复制当前目录内容，到容器中
COPY ./ .

FROM lipanski/docker-static-website:latest
COPY . .

CMD ["/busybox","httpd","-f","-v","-p","3000","-c","httpd.conf"]