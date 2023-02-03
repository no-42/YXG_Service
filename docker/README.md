### docker的使用

登录docker
```shell
docker login --username=17714353449 registry.cn-hangzhou.aliyuncs.com
```

构建镜像
```shell
docker build . -t shop-admin:v0.0.1
```

修改标签
```shell
docker tag shop-admin:v0.0.1 registry.cn-hangzhou.aliyuncs.com/thetbw/shop-admin:v0.0.1
```

推送到远程
```shell
docker push registry.cn-hangzhou.aliyuncs.com/thetbw/shop-admin:v0.0.1
```