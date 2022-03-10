package mylearning.servicecomb.contract.service;


import java.util.ArrayList;
import java.util.List;

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

//  public String findPerson() {
//    return "overload";
//  }

  public String queryGender(String name, Person person) {
    Person p = null;
    if (person == null) {
      p = new Person("zhangsan", "male");
    } else {
      p = person;
    }
    return p.getGender();
  }

  public String queryGender2(String name) {
    Person p = new Person("lisi", "female");
    return p.getGender();
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

  public List<Person> listPersons(String gender) {
    List<Person> list = new ArrayList<>();

    Person person1 = new Person();
    person1.setName("java");
    Person person2 = new Person();
    person2.setName("python");

    list.add(person1);
    list.add(person2);
    return list;
  }
}
