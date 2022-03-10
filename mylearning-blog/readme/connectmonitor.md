> 仪表盘的功能主要是实时查看微服务运行时的指标

#####1. 添加`cse-handler-cloud-extension`依赖。

> 该依赖主要用于对接华为云微服务引擎的仪表盘，一般情况下可以直接通过`cse-solution-service-engine`依赖间接引入。

```xml
<dependency>
  <groupId>com.huawei.paas.cse</groupId>
  <artifactId>cse-handler-cloud-extension</artifactId>
</dependency>
```

#####2. 在microservice.yaml文件添加`cse.handler.chain.Provider.default: bizkeeper-provider`

> CSE的仪表盘借鉴 Hystrix Dashboard，同时增加了其他数据的显示，作为服务治理的决策输入。bizkeeper 模块集成了 Hystrix , 提供隔离、熔断和容错等服务故障保护能力，详细介绍可参考ServiceComb Java Chassis开发指南[隔离熔断容错](https://docs.servicecomb.io/java-chassis/zh_CN/references-handlers/bizkeeper/)章节。

```yaml
cse:
  handler:                                                                
    chain:
      Provider:
        default: bizkeeper-provider
```

> 启动服务后在日志中可看到如下日志，表示业务服务是从服务中心通过服务发现的方式(查找CseMonitoring服务)获取的monitor地址：

```log
[2020-08-11 11:57:04,648/CST][cse-monitor-datafactory-2-1][INFO]find instances[1] from service center success. service=default/CseMonitoring/latest, old revision=null, new revision=0e69ebdb8ae57c7d92420523ea0fa7cc8beceac7 org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:261)
[2020-08-11 11:57:04,649/CST][cse-monitor-datafactory-2-1][INFO]service id=53aa93c41d6111e889730255ac105513, instance id=53b9be5b1d6111e88d880255ac105713, endpoints=[rest://100.125.16.65:30109?sslEnabled=true] org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:269)
```

> 也可以直接在microservice.yaml中通过配置项`servicecomb.monitor.client.serverUri`配置仪表盘地址（若使用专享版微服务引擎，仪表盘端口为30109），显示配置该地址之后就不会再调用`CseMonitoring`服务了。

```yaml
cse:
  monitor:
    client:
      serverUri: https://cse.cn-south-1.myhuaweicloud.com:443
```
> 调用服务，在仪表盘中可以实时查看相应指标

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202008/11/173432soofz31duua9f3sx.png)


demo源码在附件