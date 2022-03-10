> 当消费端需要指定调用某个服务端实例时，可以执行如下两步操作：

1. 在服务端实例的microservice.yaml文件添加`instance_description.properties.tags.[customKey]: [customValue]`。
```yaml
instance_description:
  properties:
    tags:
      instance1: instance1
```

2. 在消费端的microservice.yaml文件添加`cse.loadbalance.servicecomb-provider.transactionControl.options.tags.[customKey]: [customValue]`。
```yaml
cse:
  loadbalance:
    servicecomb-provider:
      transactionControl:
        options:
          tags:
            instance1: instance1
```
> 如下通过日志验证该规则是否生效。
> 消费端发起初次调用时，从服务中心找到服务端两个实例：
```log
[2020-04-16 20:43:43,687/CST][pool-2-thread-1][INFO]find instances[2] from service center success. service=mylearning-servicecomb/servicecomb-provider/0.0.0+, old revision=null, new revision=b55fc86b7faeb154156df40bd6cd364ca0fe99a4 org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:261)
[2020-04-16 20:43:43,688/CST][pool-2-thread-1][INFO]service id=7bc457e5219d8f08e86830e581db052ec6845f9a, instance id=d305aca77fdf11eab5850255ac1055f5, endpoints=[rest://192.168.0.125:8080] org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:269)
[2020-04-16 20:43:43,688/CST][pool-2-thread-1][INFO]service id=7bc457e5219d8f08e86830e581db052ec6845f9a, instance id=dabaf5c27fdf11eab5850255ac1055f5, endpoints=[rest://192.168.0.125:8081] org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:269)
...
[2020-04-16 20:43:43,891/CST][pool-2-thread-1][INFO]update instances, appId=mylearning-servicecomb, microserviceName=servicecomb-provider, versionRule=0.0.0.0+, latestVersion=0.0.0.1, inputVersionCount=1, inputInstanceCount=2
  0.instanceId=dabaf5c27fdf11eab5850255ac1055f5, status=UP, version=0.0.0.1, endpoints=[rest://192.168.0.125:8081], environment=null, framework.name=servicecomb-java-chassis, framework.version=CSE:2.3.75.RC1;ServiceComb:1.3.0.B010RC1
  1.instanceId=d305aca77fdf11eab5850255ac1055f5, status=UP, version=0.0.0.1, endpoints=[rest://192.168.0.125:8080], environment=null, framework.name=servicecomb-java-chassis, framework.version=CSE:2.3.75.RC1;ServiceComb:1.3.0.B010RC1 org.apache.servicecomb.serviceregistry.consumer.MicroserviceVersionRule.printData(MicroserviceVersionRule.java:145)
```
> 启用负载均衡规则
```log
[2020-04-16 20:43:43,967/CST][pool-2-thread-1][INFO]Using load balance rule org.apache.servicecomb.loadbalance.RoundRobinRuleExt for microservice servicecomb-provider. org.apache.servicecomb.loadbalance.ExtensionsManager.createLoadBalancerRule(ExtensionsManager.java:52)
```
> 消费端连续发起调用，查看两个服务实例的access.log，发现请求全部路由到实例属性为`instance1: instance1`的实例

* servicecomb-consumer服务的access.log
```log
192.168.0.125 - - Thu, 16 Apr 2020 20:43:41 CST /consumer/v0/hello?name=tom 200 34 5001 HTTP/1.1 5e9852fd4b438f0c
192.168.0.125 - - Thu, 16 Apr 2020 20:43:48 CST /consumer/v0/hello?name=tom 200 34 28 HTTP/1.1 5e9853049b008a2e
192.168.0.125 - - Thu, 16 Apr 2020 20:43:49 CST /consumer/v0/hello?name=tom 200 34 57 HTTP/1.1 5e98530542261e69
192.168.0.125 - - Thu, 16 Apr 2020 20:43:50 CST /consumer/v0/hello?name=tom 200 34 15 HTTP/1.1 5e98530689a3ea09
192.168.0.125 - - Thu, 16 Apr 2020 20:43:51 CST /consumer/v0/hello?name=tom 200 34 15 HTTP/1.1 5e98530726425f18
192.168.0.125 - - Thu, 16 Apr 2020 20:43:52 CST /consumer/v0/hello?name=tom 200 34 13 HTTP/1.1 5e9853081635fb28
```
* 实例属性为`instance1: instance1`的实例的access.log
```log
192.168.0.125 - - Thu, 16 Apr 2020 20:43:45 CST /provider/v0/hello?name=servicecomb 200 34 1284 HTTP/1.1 5e9852fd4b438f0c
192.168.0.125 - - Thu, 16 Apr 2020 20:43:48 CST /provider/v0/hello?name=servicecomb 200 34 5 HTTP/1.1 5e9853049b008a2e
192.168.0.125 - - Thu, 16 Apr 2020 20:43:49 CST /provider/v0/hello?name=servicecomb 200 34 19 HTTP/1.1 5e98530542261e69
192.168.0.125 - - Thu, 16 Apr 2020 20:43:50 CST /provider/v0/hello?name=servicecomb 200 34 2 HTTP/1.1 5e98530689a3ea09
192.168.0.125 - - Thu, 16 Apr 2020 20:43:51 CST /provider/v0/hello?name=servicecomb 200 34 3 HTTP/1.1 5e98530726425f18
192.168.0.125 - - Thu, 16 Apr 2020 20:43:52 CST /provider/v0/hello?name=servicecomb 200 34 4 HTTP/1.1 5e9853081635fb28
```
* 实例属性为`instance2: instance2`的实例的access.log为空

> access.log中的最后一个字段是traceId，消费端与服务端的traceId一致，表示是一次调用请求，从traceId可以看出来所有请求全部路由到`instance1: instance1`实例。
> 停止`instance1: instance1`实例的进程，消费端接收到服务中心推送的服务端实例状态发生变化的消息
```log
[2020-04-16 20:44:26,142/CST][Service Center Task [java.util.concurrent.ThreadPoolExecutor$Worker@4aae493[State = -1, empty queue][38]]][INFO]find instances[1] from service center success. service=mylearning-servicecomb/servicecomb-provider/0.0.0+, old revision=b55fc86b7faeb154156df40bd6cd364ca0fe99a4, new revision=03813120ab6425d932539ed609e6310bf429ed1e org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:261)
[2020-04-16 20:44:26,142/CST][Service Center Task [java.util.concurrent.ThreadPoolExecutor$Worker@4aae493[State = -1, empty queue][38]]][INFO]service id=7bc457e5219d8f08e86830e581db052ec6845f9a, instance id=dabaf5c27fdf11eab5850255ac1055f5, endpoints=[rest://192.168.0.125:8081] org.apache.servicecomb.serviceregistry.registry.AbstractServiceRegistry.findServiceInstances(AbstractServiceRegistry.java:269)
[2020-04-16 20:44:26,143/CST][Service Center Task [java.util.concurrent.ThreadPoolExecutor$Worker@4aae493[State = -1, empty queue][38]]][INFO]update instances, appId=mylearning-servicecomb, microserviceName=servicecomb-provider, versionRule=0.0.0.0+, latestVersion=0.0.0.1, inputVersionCount=1, inputInstanceCount=1
  0.instanceId=dabaf5c27fdf11eab5850255ac1055f5, status=UP, version=0.0.0.1, endpoints=[rest://192.168.0.125:8081], environment=null, framework.name=servicecomb-java-chassis, framework.version=CSE:2.3.75.RC1;ServiceComb:1.3.0.B010RC1 org.apache.servicecomb.serviceregistry.consumer.MicroserviceVersionRule.printData(MicroserviceVersionRule.java:145)
```
> 消费端再次发起调用使用loadbalance规则
```log
[2020-04-16 20:44:37,768/CST][pool-2-thread-7][INFO]Using load balance rule org.apache.servicecomb.loadbalance.RoundRobinRuleExt for microservice servicecomb-provider. org.apache.servicecomb.loadbalance.ExtensionsManager.createLoadBalancerRule(ExtensionsManager.java:52)
```
接着报错
```log
[2020-04-16 20:44:37,790/CST][pool-2-thread-7][ERROR]Invoke all server failed. Operation CONSUMER  servicecomb-provider.commonService.sayHello, e=cause:ClientException,message:Load balancer does not have available server for client: default org.apache.servicecomb.loadbalance.LoadbalanceHandler$3.onExecutionFailed(LoadbalanceHandler.java:337)
[2020-04-16 20:44:37,812/CST][pool-2-thread-7][INFO]Found SPI service org.apache.servicecomb.swagger.invocation.exception.ExceptionToProducerResponseConverter, count=3. org.apache.servicecomb.foundation.common.utils.SPIServiceUtils.loadSortedService(SPIServiceUtils.java:79)
[2020-04-16 20:44:37,812/CST][pool-2-thread-7][INFO]  0. org.apache.servicecomb.swagger.invocation.validator.ConstraintViolationExceptionToProducerResponseConverter. org.apache.servicecomb.foundation.common.utils.SPIServiceUtils.loadSortedService(SPIServiceUtils.java:82)
[2020-04-16 20:44:37,813/CST][pool-2-thread-7][INFO]  1. org.apache.servicecomb.swagger.invocation.exception.InvocationExceptionToProducerResponseConverter. org.apache.servicecomb.foundation.common.utils.SPIServiceUtils.loadSortedService(SPIServiceUtils.java:82)
[2020-04-16 20:44:37,813/CST][pool-2-thread-7][INFO]  2. org.apache.servicecomb.swagger.invocation.exception.DefaultExceptionToProducerResponseConverter. org.apache.servicecomb.foundation.common.utils.SPIServiceUtils.loadSortedService(SPIServiceUtils.java:82)
[2020-04-16 20:44:37,817/CST][pool-2-thread-7][ERROR]unexpected error PRODUCER rest servicecomb-consumer.commonServiceConsumer.sayHello, org.apache.servicecomb.swagger.engine.SwaggerProducerOperation.doInvoke(SwaggerProducerOperation.java:203)
java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.servicecomb.swagger.engine.SwaggerProducerOperation.doInvoke(SwaggerProducerOperation.java:188)
	at org.apache.servicecomb.swagger.engine.SwaggerProducerOperation.syncInvoke(SwaggerProducerOperation.java:173)
	at org.apache.servicecomb.swagger.engine.SwaggerProducerOperation.invoke(SwaggerProducerOperation.java:123)
	at org.apache.servicecomb.core.handler.impl.ProducerOperationHandler.handle(ProducerOperationHandler.java:40)
	at org.apache.servicecomb.core.Invocation.next(Invocation.java:204)
	at org.apache.servicecomb.bizkeeper.BizkeeperCommand.lambda$construct$2(BizkeeperCommand.java:79)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.Observable.unsafeSubscribe(Observable.java:8666)
	at rx.internal.operators.OnSubscribeDefer.call(OnSubscribeDefer.java:52)
	at rx.internal.operators.OnSubscribeDefer.call(OnSubscribeDefer.java:36)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.Observable.unsafeSubscribe(Observable.java:8666)
	at rx.internal.operators.OnSubscribeDefer.call(OnSubscribeDefer.java:52)
	at rx.internal.operators.OnSubscribeDefer.call(OnSubscribeDefer.java:36)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.Observable.unsafeSubscribe(Observable.java:8666)
	at rx.internal.operators.OnSubscribeDefer.call(OnSubscribeDefer.java:52)
	at rx.internal.operators.OnSubscribeDefer.call(OnSubscribeDefer.java:36)
	at rx.Observable.subscribe(Observable.java:8759)
	at rx.Observable.subscribe(Observable.java:8726)
	at rx.Observable.subscribe(Observable.java:8619)
	at org.apache.servicecomb.bizkeeper.BizkeeperHandler.handle(BizkeeperHandler.java:78)
	at org.apache.servicecomb.core.Invocation.next(Invocation.java:204)
	at org.apache.servicecomb.qps.ProviderQpsFlowControlHandler.handle(ProviderQpsFlowControlHandler.java:38)
	at org.apache.servicecomb.core.Invocation.next(Invocation.java:204)
	at org.apache.servicecomb.common.rest.AbstractRestInvocation.doInvoke(AbstractRestInvocation.java:243)
	at org.apache.servicecomb.common.rest.AbstractRestInvocation.invoke(AbstractRestInvocation.java:217)
	at org.apache.servicecomb.common.rest.AbstractRestInvocation.runOnExecutor(AbstractRestInvocation.java:201)
	at org.apache.servicecomb.common.rest.AbstractRestInvocation.lambda$scheduleInvocation$0(AbstractRestInvocation.java:160)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: InvocationException: code=490;msg=CommonExceptionData [message=Cse Internal Bad Request]
	at org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory.doCreate(ExceptionFactory.java:79)
	at org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory.convertException(ExceptionFactory.java:124)
	at org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory.convertConsumerException(ExceptionFactory.java:83)
	at org.apache.servicecomb.swagger.invocation.Response.createConsumerFail(Response.java:129)
	at org.apache.servicecomb.swagger.invocation.AsyncResponse.consumerFail(AsyncResponse.java:34)
	at org.apache.servicecomb.loadbalance.LoadbalanceHandler$3.fail(LoadbalanceHandler.java:359)
	at org.apache.servicecomb.loadbalance.LoadbalanceHandler$3.lambda$onExecutionFailed$1(LoadbalanceHandler.java:342)
	at org.apache.servicecomb.core.provider.consumer.SyncResponseExecutor.waitResponse(SyncResponseExecutor.java:56)
	at org.apache.servicecomb.core.provider.consumer.InvokerUtils.innerSyncInvoke(InvokerUtils.java:75)
	at org.apache.servicecomb.provider.springmvc.reference.CseClientHttpRequest.doInvoke(CseClientHttpRequest.java:211)
	at org.apache.servicecomb.provider.springmvc.reference.CseClientHttpRequest.invoke(CseClientHttpRequest.java:201)
	at org.apache.servicecomb.provider.springmvc.reference.CseClientHttpRequest.execute(CseClientHttpRequest.java:152)
	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:660)
	at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:621)
	at org.springframework.web.client.RestTemplate.getForEntity(RestTemplate.java:320)
	at org.apache.servicecomb.provider.springmvc.reference.RestTemplateWrapper.getForEntity(RestTemplateWrapper.java:96)
	at mylearning.servicecomb.consumer.service.CommonServiceConsumer.sayHello(CommonServiceConsumer.java:30)
	... 59 more
Caused by: com.netflix.client.ClientException: Load balancer does not have available server for client: default
	at com.netflix.loadbalancer.LoadBalancerContext.getServerFromLoadBalancer(LoadBalancerContext.java:483)
	at com.netflix.loadbalancer.reactive.LoadBalancerCommand$1.call(LoadBalancerCommand.java:184)
	at com.netflix.loadbalancer.reactive.LoadBalancerCommand$1.call(LoadBalancerCommand.java:180)
	at rx.Observable.unsafeSubscribe(Observable.java:8666)
	at rx.internal.operators.OnSubscribeConcatMap.call(OnSubscribeConcatMap.java:94)
	at rx.internal.operators.OnSubscribeConcatMap.call(OnSubscribeConcatMap.java:42)
	at rx.Observable.unsafeSubscribe(Observable.java:8666)
	at rx.internal.operators.OperatorRetryWithPredicate$SourceSubscriber$1.call(OperatorRetryWithPredicate.java:131)
	at rx.internal.schedulers.TrampolineScheduler$InnerCurrentThreadScheduler.enqueue(TrampolineScheduler.java:76)
	at rx.internal.schedulers.TrampolineScheduler$InnerCurrentThreadScheduler.schedule(TrampolineScheduler.java:55)
	at rx.internal.operators.OperatorRetryWithPredicate$SourceSubscriber.onNext(OperatorRetryWithPredicate.java:83)
	at rx.internal.operators.OperatorRetryWithPredicate$SourceSubscriber.onNext(OperatorRetryWithPredicate.java:49)
	at rx.internal.util.ScalarSynchronousObservable$WeakSingleProducer.request(ScalarSynchronousObservable.java:284)
	at rx.Subscriber.setProducer(Subscriber.java:209)
	at rx.internal.util.ScalarSynchronousObservable$JustOnSubscribe.call(ScalarSynchronousObservable.java:146)
	at rx.internal.util.ScalarSynchronousObservable$JustOnSubscribe.call(ScalarSynchronousObservable.java:137)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:50)
	at rx.internal.operators.OnSubscribeLift.call(OnSubscribeLift.java:30)
	at rx.Observable.subscribe(Observable.java:8759)
	at rx.Observable.subscribe(Observable.java:8726)
	at rx.Observable.subscribe(Observable.java:8619)
	at org.apache.servicecomb.loadbalance.LoadbalanceHandler.sendWithRetry(LoadbalanceHandler.java:408)
	at org.apache.servicecomb.loadbalance.LoadbalanceHandler.handle(LoadbalanceHandler.java:221)
	at org.apache.servicecomb.core.Invocation.next(Invocation.java:204)
	at org.apache.servicecomb.qps.ConsumerQpsFlowControlHandler.handle(ConsumerQpsFlowControlHandler.java:50)
	at org.apache.servicecomb.core.Invocation.next(Invocation.java:204)
	at org.apache.servicecomb.core.provider.consumer.InvokerUtils.innerSyncInvoke(InvokerUtils.java:73)
	... 67 more
```
> 消费端access.log记录请求失败
```log
192.168.0.125 - - Thu, 16 Apr 2020 20:44:37 CST /consumer/v0/hello?name=tom 490 38 108 HTTP/1.1 5e985335b2c87fe2
```

> 关于loadbalance的详细介绍，请参考ServiceComb开发者文档[负载均衡](https://docs.servicecomb.io/java-chassis/zh_CN/references-handlers/loadbalance/)章节。

demo源码在附件