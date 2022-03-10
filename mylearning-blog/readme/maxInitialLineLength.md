>当调用get类型的接口返回`414Request-URI Too Long`信息时，说明请求参数超过了最大限制。

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202008/13/150712avil4bzqykfbndza.png)

>**servicecomb-java-chassis**获取服务最大请求参数长度的方法`org.apache.servicecomb.transport.rest.vertx.TransportConfig#getMaxInitialLineLength`，默认取`io.vertx.core.http.HttpServerOptions.DEFAULT_MAX_INITIAL_LINE_LENGTH`值为`4096`，当请求参数超过该大小时就会返回该信息。

```java
public final class TransportConfig {
//other code
public static int getMaxInitialLineLength() {
    return DynamicPropertyFactory.getInstance()
        .getIntProperty("servicecomb.rest.server.maxInitialLineLength",
            HttpServerOptions.DEFAULT_MAX_INITIAL_LINE_LENGTH)
        .get();
  }
}
```

> 通过`getMaxInitialLineLength`方法的源码可以看到，**servicecomb-java-chassis**还会取`servicecomb.rest.server.maxInitialLineLength`的值，当实际请求参数大小超过默认值时，可以在microservice.yaml中通过该配置项设置修改该限制值。

```yaml
servicecomb:
  rest:
    server:
      maxInitialLineLength: 8192
```

> 其他网络相关参数可以参考开发者指导中[Rest over Vertx](https://docs.servicecomb.io/java-chassis/zh_CN/transports/rest-over-vertx/)章节。



