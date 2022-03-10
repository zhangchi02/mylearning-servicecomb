accesslog可以记录发起调用请求的地址，调用的url以及状态码和响应时间等信息，对于调用问题的定位分析有至关重要的作用，因此建议开启accesslog。

servicecomb-java-chassis提供了基于 Vert.x 的 access log，详细指导可参考： **[Access log configuration](https://docs.servicecomb.io/java-chassis/zh_CN/build-provider/access-log-configuration/)**

实际使用过程中（特别是springboot集成servicecomb时）可能会遇到开启了accesslog但并没有打印日志的情况，可能原因如下：

1、springboot默认使用logback作为日志框架，实际生产环境选择log4j2等其他日志框架，未提供一个名为"accesslog"的logger供access log打印组件使用
2、服务使用Rest over Servlet通信通道，未使用Vert.x作为http服务器

解决方案：
1、

参考开发者指南开发了一个简单的demo：
>- springboot2-provider 第三方服务，使用SpringBoot开发
>- servicecomb-consumer 消费端，使用ServiceComb开发

demo源码在附件

开发过程中发现几个注意点：
>1. 抽象类中url要与第三方服务的url一致
>2. 抽象类中方法名称不需要与第三方服务一致
>3. 抽象类方法返回值对象最好与第三方服务一致（包名+类名），否则会报类转换异常
>4. 由于ServiceComb调用以契约为准，因此生成的服务契约一定要契合第三方服务的接口规范（例如demo中的header）
>5. 若第三方服务开启了TLS通信，在注册第三方服务时endpoint需要添加`?sslEnabled=true`,同时参考开发者指南中**[使用TLS通信](https://docs.servicecomb.io/java-chassis/zh_CN/security/tls/)**章节中**证书配置**部分配置证书