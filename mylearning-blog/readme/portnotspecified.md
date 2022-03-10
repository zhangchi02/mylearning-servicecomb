服务启动失败，报错"port not specified"。

报错日志：
```log
Exception in thread "main" java.lang.IllegalStateException: ServiceComb init failed.
	at org.apache.servicecomb.core.SCBEngine.run(SCBEngine.java:309)
	at org.apache.servicecomb.core.CseApplicationListener.onApplicationEvent(CseApplicationListener.java:77)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139)
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:402)
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:359)
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:896)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:552)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:144)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:95)
	at org.apache.servicecomb.foundation.common.utils.BeanUtils.init(BeanUtils.java:62)
	at org.apache.servicecomb.foundation.common.utils.BeanUtils.init(BeanUtils.java:53)
	at com.zhc.HelloWorldProviderMain.main(HelloWorldProviderMain.java:39)
Caused by: java.lang.IllegalArgumentException: port not specified.
	at org.apache.servicecomb.foundation.common.net.URIEndpointObject.<init>(URIEndpointObject.java:51)
	at org.apache.servicecomb.core.transport.AbstractTransport.parseAddress(AbstractTransport.java:151)
	at org.apache.servicecomb.core.Endpoint.<init>(Endpoint.java:45)
	at org.apache.servicecomb.core.Endpoint.<init>(Endpoint.java:38)
	at org.apache.servicecomb.core.transport.AbstractTransport.setListenAddressWithoutSchema(AbstractTransport.java:89)
	at org.apache.servicecomb.core.transport.AbstractTransport.setListenAddressWithoutSchema(AbstractTransport.java:76)
	at org.apache.servicecomb.transport.rest.vertx.VertxRestTransport.canInit(VertxRestTransport.java:52)
	at org.apache.servicecomb.core.transport.TransportManager.chooseOneTransport(TransportManager.java:90)
	at org.apache.servicecomb.core.transport.TransportManager.buildTransportMap(TransportManager.java:81)
	at org.apache.servicecomb.core.transport.TransportManager.init(TransportManager.java:57)
	at org.apache.servicecomb.core.SCBEngine.doRun(SCBEngine.java:349)
	at org.apache.servicecomb.core.SCBEngine.run(SCBEngine.java:297)
	... 13 more

```

> 检查服务相关配置没有问题，可以实现`org.apache.servicecomb.core.BootListener`接口查看最终使用的服务端口相关配置的值。

```java
@Component
  public class MytListener implements BootListener {
    private static final Logger LOG = LoggerFactory.getLogger(MytListener.class);
  
    @Override
    public void onBootEvent(BootEvent event) {
      switch (event.getEventType()) {
        case BEFORE_PRODUCER_PROVIDER:
          LOG.info("cse.rest.address: {}.",
              DynamicPropertyFactory.getInstance().getStringProperty("cse.rest.address", "").get());
          LOG.info("cse.service.publishAddress: {}.",
              DynamicPropertyFactory.getInstance().getStringProperty("cse.service.publishAddress", "").get());
          LOG.info("cse.rest.publishPort: {}.",
              DynamicPropertyFactory.getInstance().getStringProperty("cse.rest.publishPort", "").get());
          break;
        default:
          break;
      }
    }
  }
```
> 重启服务，在日志中查看到`cse.service.publishAddress`的值`zookeeperadapter-92720-0.zookeeperadapter-92720-none.12950`不符合uri规范，`.`后面不能是纯数字开头，`12950`前面需要加字母

```log
[2020-05-13 19:38:23,230/CST][main][INFO]cse.rest.address: 0.0.0.0:8080. com.zhc.listener.MytListener.onBootEvent(MytListener.java:18)
[2020-05-13 19:38:23,230/CST][main][INFO]cse.service.publishAddress: zookeeperadapter-92720-0.zookeeperadapter-92720-none.12950. com.zhc.listener.MytListener.onBootEvent(MytListener.java:20)
[2020-05-13 19:38:23,230/CST][main][INFO]cse.rest.publishPort: . com.zhc.listener.MytListener.onBootEvent(MytListener.java:22)
```
> 修改`cse.service.publishAddress`的值为`zookeeperadapter-92720-0.zookeeperadapter-92720-none.a12950`,服务可以正常启动了。

```log
[2020-05-13 19:47:34,868/CST][main][INFO]cse.rest.address: 0.0.0.0:8080. com.zhc.listener.MytListener.onBootEvent(MytListener.java:18)
[2020-05-13 19:47:34,868/CST][main][INFO]cse.service.publishAddress: zookeeperadapter-92720-0.zookeeperadapter-92720-none.a12950. com.zhc.listener.MytListener.onBootEvent(MytListener.java:20)
[2020-05-13 19:47:34,868/CST][main][INFO]cse.rest.publishPort: . com.zhc.listener.MytListener.onBootEvent(MytListener.java:22)
```



