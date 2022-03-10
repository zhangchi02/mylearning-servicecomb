> 调用服务时报错`locate path failed`

```log
locate path failed, status:Not Found, http method:x, path:xxx, microserviceName:xx org.apache.servicecomb.common.rest.locator.OperationLocator.locate(OperationLocator.java:72).
```

排查思路：

服务中心是否有该接口的契约

服务中心中无该接口的契约

服务使用隐式契约开发

1）需要注意Service是否被加载到，可参考：https://bbs.huaweicloud.com/forum/thread-52848-1-1.html

2）cse的约束https://bbs.huaweicloud.com/forum/thread-14621-1-1.html

服务使用显示契约开发，需要查看是否在契约中新增该接口的契约。

服务中心中无该接口的契约

需保证调用端（consumer/edge）获取到最新版本的服务短契约

若服务使用显示契约，主要查看显示契约中operationId是否与类中方法名一致

若服务使用隐式契约
若通过edge转发，注意排查转发路径是否准确
通过consumer调用，






```log
Exception in thread "main" java.lang.IllegalStateException: ServiceComb init failed.
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
	at mylearning.servicecomb.ServiceCombConsumerMain.main(ServiceCombConsumerMain.java:23)
Caused by: java.lang.Error: generate operation swagger failed, mylearning.servicecomb.service.ThirdPartyService:thirdSayHello
	at org.apache.servicecomb.swagger.generator.core.SwaggerGenerator.scanMethods(SwaggerGenerator.java:252)
	at org.apache.servicecomb.swagger.generator.core.SwaggerGenerator.generate(SwaggerGenerator.java:141)
	at org.apache.servicecomb.core.definition.schema.AbstractSchemaFactory.generateSwagger(AbstractSchemaFactory.java:102)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.createSchema(ProducerSchemaFactory.java:115)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.createSchema(ProducerSchemaFactory.java:54)
	at org.apache.servicecomb.core.definition.schema.AbstractSchemaFactory.getOrCreateSchema(AbstractSchemaFactory.java:58)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.getOrCreateProducerSchema(ProducerSchemaFactory.java:76)
	at org.apache.servicecomb.provider.rest.common.RestProducerProvider.init(RestProducerProvider.java:45)
	at org.apache.servicecomb.core.provider.producer.ProducerProviderManager.init(ProducerProviderManager.java:54)
	at org.apache.servicecomb.core.SCBEngine.doInit(SCBEngine.java:264)
	at org.apache.servicecomb.core.SCBEngine.init(SCBEngine.java:219)
	... 13 more
Caused by: org.apache.servicecomb.foundation.common.exceptions.ServiceCombException: [simple type, class javax.servlet.http.HttpServletResponse] is interface. Must be a concrete type.
	at org.apache.servicecomb.swagger.extend.ModelResolverExt.checkType(ModelResolverExt.java:119)
	at org.apache.servicecomb.swagger.extend.ModelResolverExt.resolveProperty(ModelResolverExt.java:151)
	at io.swagger.jackson.ModelResolver.resolveProperty(ModelResolver.java:125)
	at io.swagger.converter.ModelConverterContextImpl.resolveProperty(ModelConverterContextImpl.java:83)
	at io.swagger.converter.ModelConverters.readAsProperty(ModelConverters.java:63)
	at io.swagger.converter.ModelConverters.readAsProperty(ModelConverters.java:57)
	at org.apache.servicecomb.swagger.generator.springmvc.processor.parameter.SpringmvcDefaultParameterProcessor.process(SpringmvcDefaultParameterProcessor.java:39)
	at org.apache.servicecomb.swagger.generator.core.OperationGenerator.scanMethodParameters(OperationGenerator.java:298)
	at org.apache.servicecomb.swagger.generator.core.OperationGenerator.generate(OperationGenerator.java:181)
	at org.apache.servicecomb.swagger.generator.core.SwaggerGenerator.scanMethods(SwaggerGenerator.java:248)
	... 23 more
```

> 服务报这个错误是由于在接口定义中使用了接口类型的参数`HttpServletResponse`，ServiceComb关于接口定义和数据类型的支持可参考开发者指导[接口定义和数据类型](https://docs.servicecomb.io/java-chassis/zh_CN/build-provider/interface-constraints/)

```java
  @RequestMapping(path = "/thirdSayHello", method = RequestMethod.GET)
  public String thirdSayHello(@RequestParam(name = "name") String name, HttpServletResponse HttpServletResponse) {
    // invoke 3rd party rest service
    String response = commonServerIntf.sayHello(name);
    LOGGER.info("name: {}.", response);
    return response;
  }
```



