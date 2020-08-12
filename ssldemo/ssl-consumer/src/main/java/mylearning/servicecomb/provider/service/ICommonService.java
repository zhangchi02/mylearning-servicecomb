package mylearning.servicecomb.provider.service;

public interface ICommonService {
  //方法名称与provider服务契约中的operationId保持一致
  //参数顺序与provider服务契约中定义的顺序保持一致
  String sayHello(String name);

  String testPathVariable(String path) throws Exception;

}
