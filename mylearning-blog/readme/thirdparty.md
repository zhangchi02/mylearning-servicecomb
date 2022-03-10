开发者指南链接已改变：**[调用第三方服务](https://docs.servicecomb.io/java-chassis/zh_CN/build-consumer/3rd-party-service-invoke/)**

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