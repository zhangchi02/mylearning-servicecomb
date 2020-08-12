package mylearning.servicecomb.contract.service;

import javax.validation.Valid;

import mylearning.servicecomb.contract.common.MyDTO;
import mylearning.servicecomb.contract.common.Person;

public interface RPCService {
  Person findPerson(String name);

  String queryGender(String name);

  Person savePerson(Person person);

  MyDTO saveMyDTO(@Valid MyDTO myDTO);
}
