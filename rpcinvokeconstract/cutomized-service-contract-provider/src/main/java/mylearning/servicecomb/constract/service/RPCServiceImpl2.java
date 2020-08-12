package mylearning.servicecomb.constract.service;


import javax.validation.Valid;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.constract.common.MyDTO;
import mylearning.servicecomb.constract.common.Person;

@RpcSchema(schemaId = "RPCService2")
public class RPCServiceImpl2 implements RPCService {
  private static final Logger LOG = LoggerFactory.getLogger(RPCServiceImpl2.class);

  public Person findPerson(String name) {
    Person person = new Person();
    person.setName("Java");
    person.setGender("male");
    return person;
  }

  public String queryGender(String name) {
    Person person = new Person("java", "male");
    String gender = "";
    if ("java".equalsIgnoreCase(name)) {
      gender = person.getGender();
    }
    return gender;
  }

  public Person savePerson(Person person) {
    return person;
  }

  public Person savePerson2(String name, Person person) {
    Person person2 = new Person("java", "male");
    return person2;
  }

  public MyDTO saveMyDTO(@Valid MyDTO myDTO) {
    LOG.info("myDTO:{}.", myDTO);
    return myDTO;
  }
}
