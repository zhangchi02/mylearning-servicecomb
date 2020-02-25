package mylearning.servicecomb.springboot.vertx.jar.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mylearning.servicecomb.springboot.vertx.jar.sample.Hello;
import mylearning.servicecomb.springboot.vertx.jar.sample.Person;

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
