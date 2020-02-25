package mylearning.servicecomb.contract.service;

import mylearning.servicecomb.contract.common.Person;

public interface RPCService {
  Person findPerson(String name);
  String queryGender(String name);
  Person savePerson(Person person);
}
