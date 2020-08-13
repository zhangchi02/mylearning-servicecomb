package mylearning.servicecomb.pojo.servlet.consumer.service;

import org.apache.servicecomb.provider.pojo.Invoker;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;
//import org.springframework.stereotype.Service;

import mylearning.servicecomb.pojo.servlet.consumer.sample.Hello;
import mylearning.servicecomb.pojo.servlet.consumer.sample.Person;

@RpcSchema(schemaId = "hello")
public class HelloService implements Hello{
  @RpcReference(microserviceName = "provider", schemaId = "hello")
  private static Hello hello;

  @Override
  public  String sayHi(String name) {
    return hello.sayHi(name);
  }

  @Override
  public String sayHello(Person person) {
    Hello helo = Invoker.createProxy("provider", "hello", Hello.class);
    String result = helo.sayHello(person);
    return result;
  }
}
