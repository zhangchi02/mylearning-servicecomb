package mylearning.servicecomb.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mylearning.servicecomb.common.Person;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
//定义RPC调用方式所使用的代理接口
public interface ICommonService {
  //方法名称与provider服务契约中的operationId保持一致
  //参数顺序与provider服务契约中定义的顺序保持一致
  String sayHello(String name);

  String testPathVariable(String path) throws Exception;

  String listPerson(List<Person> personsList);
}
