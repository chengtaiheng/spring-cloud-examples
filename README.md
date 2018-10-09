# SpringCloud演示项目

本项目用`docker-compose`启动，请参考[docker-compose.yml](https://github.com/yingzhuo/spring-cloud-examples/blob/master/dc/docker-compose.yml)。

### 注意事项

* 本项目由`Scala`语言编写，如果试图通过IDE，如`Eclipse`或`IDEA`，请先安装其对应的`Scala`插件。
* 本项目所有节点都通过`docker`容器运行。为了方便管理所有节点都运行在名为`mirai`的network上。应当预先创建这个network。(`sudo docker create network mirai`)
* 构建镜像由`apache-maven`完成，其版本号不得低于`3.5.4`。请参考构建脚本[rebuild.sh](https://github.com/yingzhuo/spring-cloud-examples/blob/master/rebuild.sh)
