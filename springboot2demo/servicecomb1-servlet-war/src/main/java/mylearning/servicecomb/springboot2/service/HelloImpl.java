package mylearning.servicecomb.springboot2.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.springboot2.sample.Hello;
import mylearning.servicecomb.springboot2.sample.Person;

@RpcSchema(schemaId = "HelloImpl")
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
    Person person = new Person("Java", 23);
    return person;
  }
}
