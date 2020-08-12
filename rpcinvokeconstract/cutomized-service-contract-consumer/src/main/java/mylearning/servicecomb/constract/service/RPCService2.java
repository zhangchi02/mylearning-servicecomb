package mylearning.servicecomb.constract.service;

import javax.validation.Valid;

import mylearning.servicecomb.constract.common2.MyDTO;
import mylearning.servicecomb.constract.common2.Person;

public interface RPCService2 {
  Person findPerson(String name);

  String queryGender(String name);

  Person savePerson(Person person);

  MyDTO saveMyDTO(@Valid MyDTO myDTO);
}
