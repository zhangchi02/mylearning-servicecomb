package mylearning.servicecomb.intf;

import mylearning.servicecomb.constract.common.Person;

public interface RPCService {
  Person findPerson(String name);
}
