> 每个微服务的schema要保证该微服务内唯一，否则会导致服务启动失败

* RPC开发方式服务启动报错：`Not allow register repeat data, name=xxx, key=xxx`
```log
2020-04-21 11:23:32.228 [main] ERROR org.apache.servicecomb.foundation.common.RegisterManager - Not allow register repeat data, name=pojo service manager, key=multipleParamService
Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'multipleParamService' defined in file [D:\Projects\mylearning-servicecomb\rpcinvokedifferentpackagepath\pojo-vertx-provider\target\classes\mylearning\servicecomb\pojo\vertx\provider\service\MultipleParamService.class]: Initialization of bean failed; nested exception is java.lang.Error: Not allow register repeat data, name=pojo service manager, key=multipleParamService
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:562)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:481)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:312)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:308)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:761)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:867)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:543)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:139)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:93)
	at org.apache.servicecomb.foundation.common.utils.BeanUtils.init(BeanUtils.java:54)
	at org.apache.servicecomb.foundation.common.utils.BeanUtils.init(BeanUtils.java:47)
	at mylearning.servicecomb.pojo.vertx.provider.PojoVertxProviderMain.main(PojoVertxProviderMain.java:9)
Caused by: java.lang.Error: Not allow register repeat data, name=pojo service manager, key=multipleParamService
	at org.apache.servicecomb.foundation.common.RegisterManager.register(RegisterManager.java:62)
	at org.apache.servicecomb.provider.pojo.schema.PojoProducers.registerPojoProducer(PojoProducers.java:35)
	at org.apache.servicecomb.provider.pojo.schema.PojoProducers.processProvider(PojoProducers.java:89)
	at org.apache.servicecomb.provider.pojo.schema.PojoProducers.postProcessAfterInitialization(PojoProducers.java:57)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(AbstractAutowireCapableBeanFactory.java:421)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1635)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:553)
	... 13 more
```

* SpringMVC和JaxRS开发方式服务动报错：`swagger method xxx not exist in xxx`
```log
java.lang.IllegalStateException: ServiceComb init failed.
	at org.apache.servicecomb.core.SCBEngine.init(SCBEngine.java:231)
	at org.apache.servicecomb.core.CseApplicationListener.onApplicationEvent(CseApplicationListener.java:81)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139)
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:393)
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:347)
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:883)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:546)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:139)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:93)
	at org.apache.servicecomb.foundation.common.utils.BeanUtils.init(BeanUtils.java:54)
	at org.apache.servicecomb.foundation.common.utils.BeanUtils.init(BeanUtils.java:47)
	at com.zhc.HelloWorldProviderMain.main(HelloWorldProviderMain.java:39)
Caused by: java.lang.Error: swagger method shutdown not exist in producer com.zhc.service.CookieService.
	at org.apache.servicecomb.swagger.engine.SwaggerEnvironment.createProducer(SwaggerEnvironment.java:181)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.getOrCreateProducerSchema(ProducerSchemaFactory.java:78)
	at org.apache.servicecomb.provider.rest.common.RestProducerProvider.init(RestProducerProvider.java:45)
	at org.apache.servicecomb.core.provider.producer.ProducerProviderManager.init(ProducerProviderManager.java:54)
	at org.apache.servicecomb.core.SCBEngine.doInit(SCBEngine.java:264)
	at org.apache.servicecomb.core.SCBEngine.init(SCBEngine.java:219)
	... 13 more
```
> 当服务报上述错误时，需要根据日志的提示信息对schema进行排查，修改重名的`schemaId`。

