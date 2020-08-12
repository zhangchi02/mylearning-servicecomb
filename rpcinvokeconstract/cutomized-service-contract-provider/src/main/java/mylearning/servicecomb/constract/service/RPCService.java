package mylearning.servicecomb.constract.service;

import javax.validation.Valid;

import mylearning.servicecomb.constract.common.MyDTO;
import mylearning.servicecomb.constract.common.Person;

public interface RPCService {
  Person findPerson(String name);

  String queryGender(String name);

  Person savePerson(Person person);

  MyDTO saveMyDTO(@Valid MyDTO myDTO);
}
