package mylearning.servicecomb.pojo.servlet.provider.sample;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
  
  public ResponseEntity<Person> exception(String type) {
    Person person = new Person("Java",23);
    ResponseEntity<Person> response = null;
    if(type.equalsIgnoreCase("exception")) {
      response = new ResponseEntity<Person>(person,HttpStatus.NOT_FOUND);
    }else {
      response = new ResponseEntity<Person>(person,HttpStatus.OK);
    }
    return response;
  }
}
