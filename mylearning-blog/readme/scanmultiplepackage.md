> 服务启动正常，在服务中心可以查看到注册的服务实例，但在调用服务时返回404.

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/26/093325xcbhotn6akguxors.png)

> 查看日志报错`"No schema defined for xxx:xxx"`

```log
[2020-04-26 09:47:26,878/CST][vert.x-eventloop-thread-4][ERROR]No schema defined for mylearning-servicecomb:servicecomb-consumer. org.apache.servicecomb.common.rest.AbstractRestInvocation.findRestOperation(AbstractRestInvocation.java:82)
```

查看服务中心中该服务的契约为空，同时在服务的启动日志中搜不到`"generate swagger"`关键字

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/26/094835j9pwrcocdjnux6el.png)

若代码没有问题，需要查看代码目录和日志，检查service所在目录是否被加载

* 代码目录

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/26/095605u0ujobt77qn43cz7.png)

* 服务启动日志

```log
[2020-04-26 10:58:10,486/CST][main][INFO]Scb scan package list: org.apache.servicecomb,common.consumer org.apache.servicecomb.foundation.common.utils.BeanUtils.prepareServiceCombScanPackage(BeanUtils.java:93)
[2020-04-26 10:58:10,528/CST][main][INFO]Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@54c562f7: startup date [Sun Apr 26 10:58:10 CST 2020]; root of context hierarchy org.springframework.context.support.AbstractApplicationContext.prepareRefresh(AbstractApplicationContext.java:583)
[2020-04-26 10:58:10,674/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/foundation-config/1.3.0/foundation-config-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 10:58:10,842/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/foundation-vertx/1.3.0/foundation-vertx-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 10:58:10,868/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/java-chassis-core/1.3.0/java-chassis-core-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 10:58:11,638/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/handler-bizkeeper/1.3.0/handler-bizkeeper-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 10:58:11,654/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/handler-loadbalance/1.3.0/handler-loadbalance-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 10:58:11,664/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/com/huawei/paas/cse/cse-handler-cloud-extension/2.5.0/cse-handler-cloud-extension-2.5.0.jar!/META-INF/spring/services.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)

```

> 经检查发现默认只会提前扫描`org.apache.servicecomb`和`common.consumer`包，其他的类都通过`xxx.bean.xml`文件引入，所以需要在resources目录下新增`META-INF/spring/xxx.bean.xml`，指定要扫描的路径，多个路径之间以`;`分隔开

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/26/100128xkt4iochqxq96dkx.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:context="http://www.springframework.org/schema/context"
  xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

  <context:component-scan base-package="mylearning.servicecomb;common.consumer"/>
</beans>
```

> 重启服务，发现日志里加载了新建的`xxx.bean.xml`文件，同时也打印了生成服务契约的日志，在服务中心也可以查看到服务契约

```log
[2020-04-26 11:43:33,563/CST][main][INFO]Scb scan package list: org.apache.servicecomb,common.consumer org.apache.servicecomb.foundation.common.utils.BeanUtils.prepareServiceCombScanPackage(BeanUtils.java:93)
[2020-04-26 11:43:33,638/CST][main][INFO]Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@54c562f7: startup date [Sun Apr 26 11:43:33 CST 2020]; root of context hierarchy org.springframework.context.support.AbstractApplicationContext.prepareRefresh(AbstractApplicationContext.java:583)
[2020-04-26 11:43:33,763/CST][main][INFO]Loading XML bean definitions from file [D:\Projects\mylearning-servicecomb\scanmultiplepackage\serviecomb-consumer\target\classes\META-INF\spring\servicecomb.consumer.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 11:43:34,297/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/foundation-config/1.3.0/foundation-config-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 11:43:34,322/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/foundation-vertx/1.3.0/foundation-vertx-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 11:43:34,336/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/java-chassis-core/1.3.0/java-chassis-core-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 11:43:35,214/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/handler-bizkeeper/1.3.0/handler-bizkeeper-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 11:43:35,226/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/org/apache/servicecomb/handler-loadbalance/1.3.0/handler-loadbalance-1.3.0.jar!/META-INF/spring/cse.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
[2020-04-26 11:43:35,240/CST][main][INFO]Loading XML bean definitions from URL [jar:file:/D:/Repository/com/huawei/paas/cse/cse-handler-cloud-extension/2.5.0/cse-handler-cloud-extension-2.5.0.jar!/META-INF/spring/services.bean.xml] org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317)
...
[2020-04-26 11:43:40,452/CST][main][INFO]generate swagger for mylearning-servicecomb/servicecomb-consumer/commonService, swagger: ---
swagger: "2.0"
info:
  version: "1.0.0"
  title: "swagger definition for mylearning.servicecomb.consumer.service.CommonService"
  x-java-interface: "cse.gen.mylearning_servicecomb.servicecomb_consumer.commonService.CommonServiceIntf"
basePath: "/consumer/v0/"
consumes:
- "application/json"
produces:
- "application/json"
paths:
  /hello:
    get:
      operationId: "hello"
      parameters:
      - name: "name"
        in: "query"
        required: true
        type: "string"
      responses:
        200:
          description: "response of 200"
          schema:
            type: "string"
  /hi:
    get:
      operationId: "hi"
      parameters:
      - name: "name"
        in: "query"
        required: true
        type: "string"
      responses:
        200:
          description: "response of 200"
          schema:
            type: "string"
 org.apache.servicecomb.core.definition.schema.ProducerSchemaFactory.createSchema(ProducerSchemaFactory.java:118)
```

> 调用服务可以正常返回

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/26/202643gekyxftb9q9lntey.png)


