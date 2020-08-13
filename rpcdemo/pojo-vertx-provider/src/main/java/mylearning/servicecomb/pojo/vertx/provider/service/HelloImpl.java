package mylearning.servicecomb.pojo.vertx.provider.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.pojo.common.sample.Person;

@RpcSchema(schemaId = "hello")
public class HelloImpl{
//public class HelloImpl implements Hello {
//  @Override
  public String sayHi(String name) {
    return "Hello " + name;
  }

//  @Override
  public String sayHello(Person person) {
    return "Hello person " + person.getName();
  }


}
