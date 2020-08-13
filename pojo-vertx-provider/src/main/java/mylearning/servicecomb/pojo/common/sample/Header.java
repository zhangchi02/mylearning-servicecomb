package mylearning.servicecomb.pojo.common.sample;

public interface Header {
  String checkHeader(String name, String id, Integer age, String gender);

  String withoutParam(String product);

  String sayHello(Person person);
}
