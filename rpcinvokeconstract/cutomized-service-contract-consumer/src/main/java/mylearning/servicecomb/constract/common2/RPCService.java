package mylearning.servicecomb.constract.common2;

import javax.validation.Valid;

public interface RPCService {
  Person findPerson(String name);

  String queryGender(String name);

  Person savePerson(Person person);

  MyDTO saveMyDTO(@Valid MyDTO myDTO);
}
