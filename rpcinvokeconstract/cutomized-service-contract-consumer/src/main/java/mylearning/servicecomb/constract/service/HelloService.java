package mylearning.servicecomb.constract.service;

import mylearning.servicecomb.constract.common.BaseResponse;
import mylearning.servicecomb.constract.common.Person;
import mylearning.servicecomb.constract.common.User;

public interface HelloService {
  Person findPerson(String name);

  BaseResponse<User> findUser();

  BaseResponse<User> findUser2();

  String findUser3();

  String findGender(String name);

  String findGender2(String name);

  String uniteInvoke(String microserviceName, String schemaId, String operationName, String param);
}
