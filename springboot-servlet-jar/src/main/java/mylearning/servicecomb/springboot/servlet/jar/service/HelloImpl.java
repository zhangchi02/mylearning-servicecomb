package mylearning.servicecomb.springboot.servlet.jar.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.springboot.servlet.jar.sample.Hello;
import mylearning.servicecomb.springboot.servlet.jar.sample.Person;

@RpcSchema(schemaId = "hello")
public class HelloImpl implements Hello {
  @Override
  public String sayHi(String name) {
    return "Hello " + name;
  }

  @Override
  public String sayHello(Person person) {
    return "Hello person " + person.getName();
  }
  
  public Person findPerson(String type) {
    Person person = new Person("Java",23);
    return person;
  }
}
