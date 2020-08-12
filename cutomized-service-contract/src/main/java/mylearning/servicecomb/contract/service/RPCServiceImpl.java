package mylearning.servicecomb.contract.service;


import javax.validation.Valid;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.contract.common.MyDTO;
import mylearning.servicecomb.contract.common.Person;

@RpcSchema(schemaId = "RPCService")
public class RPCServiceImpl implements RPCService {
  private static final Logger LOG = LoggerFactory.getLogger(RPCServiceImpl.class);

  public Person findPerson(String name) {
    Person person = new Person();
    person.setName("Java");
    person.setGender("male");
    return person;
  }

  public String findPerson() {
    return "overload";
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
