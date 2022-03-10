消费端（发起调用方）在调用服务端（被调用方）时报如下错误：

```log
Probably invoke a service before it is registered, or no instance found for it, appId=mylearning-servicecomb, name=provider
```

可能导致该错误的原因：

+ 1.服务端未注册或没有在线的服务实例；

排查步骤：登录微服务引擎，查看服务中心`服务目录`>`实例列表`中是否有服务端的实例；

+ 2.消费端实例与服务端实例的`微服务环境`不一致；

排查步骤：检查服务端实例的`微服务环境`（`microservice.yaml`文件中的`service_description.environment`）是否与消费端实例一致，只有相同微服务环境的实例才能正常调用;

+ 3.消费端调用的应用名或者服务名与在线服务端实例不一致；

排查步骤：检查日志中`appId`和`name`是否与服务端实例的`微服务应用`以及`微服务名称`一致，注意微服务应用与名称区分大小写;

+ 4.服务端契约有问题，导致消费端在调用服务端时加载契约失败（常见于使用自定义服务契约的情况）；

排查步骤：

1.在报错日志`Probably invoke`之前搜索ERROR或者WARN级别的日志，可以搜索到`Failed to setInstances`

```log
[2020-08-05 15:09:49,040/CST][pool-2-thread-1][ERROR]Failed to setInstances, appId=mylearning-servicecomb, microserviceName=contractfirst. org.apache.servicecomb.serviceregistry.consumer.MicroserviceVersions.safeSetInstances(MicroserviceVersions.java:170)
```

2.在服务中心中找到消费端要调用的服务端schema服务契约，选择yaml类型并复制

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202008/05/161812yq5fqzqhtepxxcbg.png)

3）将服务契约拷贝到[swagger在线编辑器](https://editor.swagger.io/)会自动提示何处契约有问题


参考：[https://github.com/apache/servicecomb-java-chassis/issues/1431](https://github.com/apache/servicecomb-java-chassis/issues/1431)
