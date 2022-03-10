package mylearning.servicecomb.intf;

import java.util.List;

import mylearning.servicecomb.constract.common.Person;

public interface RPCService {
  Person findPerson(String name);

//  <T> T queryGender(String name);
  String  queryGender(String name);

//  <T> List<T> listPersons(String gender);
  List<Person> listPersons(String gender);
}
