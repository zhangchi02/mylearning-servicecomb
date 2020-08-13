package mylearning.servicecomb.pojo.vertx.consumer.service;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.pojo.common.sample.Hello;

@RpcSchema(schemaId = "helloService")
public class HelloService {
  @RpcReference(microserviceName = "provider", schemaId = "hello")
  private static Hello hello;
  
  public static String sayHi(String name) {
    return hello.sayHi(name);
  }
  
}
