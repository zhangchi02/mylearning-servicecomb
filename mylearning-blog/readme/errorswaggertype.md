> 服务启动时可以正常生成契约，但报NullPointerException，详细日志如下：

```log
[2020-04-21 19:16:50,158/CST][main][INFO]generate swagger for mylearning-servicecomb/provider/swagger, swagger: ---
swagger: "2.0"
info:
  version: "1.0.0"
  title: "swagger definition for com.zhc.service.SwaggerService"
  x-java-interface: "cse.gen.mylearning_servicecomb.provider.swagger.SwaggerServiceIntf"
basePath: "/provider/v0"
consumes:
- "application/json"
produces:
- "application/json"
paths:
  /findTerminal:
    post:
      operationId: "findTerminal"
      parameters:
      - name: "terminal"
        in: "query"
        description: "系统类型"
        required: false
        type: "string"
      - name: "recordId"
        in: "query"
        description: "记录id"
        required: false
        type: "int"
      responses:
        "200":
          description: "response of 200"
          schema:
            type: "string"
 org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.createSchema(ProducerSchemaFactory.java:118)
[2020-04-21 19:16:50,158/CST][main][INFO]register schema mylearning-servicecomb/provider/swagger org.apache.servicecomb.core.definition.loader.SchemaLoader.registerSchema(SchemaLoader.java:84)
[2020-04-21 19:07:02,077/CST][main][ERROR]Unhandled exception to service provider schema swagger org.apache.servicecomb.core.definition.SchemaMeta.<init>(SchemaMeta.java:77)
[2020-04-21 19:07:02,077/CST][main][ERROR]Failed to start ServiceComb due to errors and close: null org.apache.servicecomb.core.SCBEngine.init(SCBEngine.java:224)
...
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
	at com.zhc.HelloWorldProviderMain.main(HelloWorldProviderMain.java:39)
Caused by: java.lang.NullPointerException
	at org.apache.servicecomb.common.javassist.CtType.<init>(CtType.java:52)
	at org.apache.servicecomb.common.javassist.MethodConfig.addParameter(MethodConfig.java:72)
	at org.apache.servicecomb.swagger.converter.SwaggerToClassGenerator.convertToInterface(SwaggerToClassGenerator.java:229)
	at org.apache.servicecomb.swagger.converter.SwaggerToClassGenerator.convert(SwaggerToClassGenerator.java:144)
	at org.apache.servicecomb.core.definition.SchemaMeta.<init>(SchemaMeta.java:70)
	at org.apache.servicecomb.core.definition.loader.SchemaLoader.registerSchema(SchemaLoader.java:86)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.createSchema(ProducerSchemaFactory.java:133)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.createSchema(ProducerSchemaFactory.java:54)
	at org.apache.servicecomb.core.definition.schema.AbstractSchemaFactory.getOrCreateSchema(AbstractSchemaFactory.java:58)
	at org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.getOrCreateProducerSchema(ProducerSchemaFactory.java:76)
	at org.apache.servicecomb.provider.rest.common.RestProducerProvider.init(RestProducerProvider.java:45)
	at org.apache.servicecomb.core.provider.producer.ProducerProviderManager.init(ProducerProviderManager.java:54)
	at org.apache.servicecomb.core.SCBEngine.doInit(SCBEngine.java:264)
	at org.apache.servicecomb.core.SCBEngine.init(SCBEngine.java:219)
	... 13 more
```
> 将生成的服务契约拷贝到[swagger在线编辑器](http://editor.swagger.io/)，可以看到契约中包含了swagger不支持的数据类型`int`。
> 根据页面的提示，swagger支持的数据类型包括：`string`, `number`, `boolean`, `integer`, `array`。

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/21/192810t8syynjezodcnhrx.png)

> 对比契约找到`findTerminal`方法，发现在使用swagger描述契约信息的时候将数据类型设置成`int`类型了。

```java
  @PostMapping("/findTerminal")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "query", name = "terminal", value = "系统类型", dataType = "string"),
      @ApiImplicitParam(paramType = "query", name = "recordId", value = "记录id", dataType = "int")
  })
  public String findTerminal(String terminal, int recordId) {
    return recordId + ":" + terminal;
  }
```
> 修改`dataType`为`integer`，服务可以正常启动。

