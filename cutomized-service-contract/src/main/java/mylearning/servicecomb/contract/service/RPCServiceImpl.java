package mylearning.servicecomb.contract.service;


import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.contract.common.Person;

@RpcSchema(schemaId = "RPCService")
public class RPCServiceImpl implements RPCService {
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
}
