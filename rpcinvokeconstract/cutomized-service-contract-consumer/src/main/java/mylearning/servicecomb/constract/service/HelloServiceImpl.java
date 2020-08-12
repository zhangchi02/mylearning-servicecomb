package mylearning.servicecomb.constract.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.servicecomb.core.provider.consumer.InvokerUtils;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.constract.common.BaseResponse;
import mylearning.servicecomb.constract.common.Person;
import mylearning.servicecomb.constract.common.User;
//java.lang.IllegalStateException: Schema not exist, microserviceName=constract-provider, schemaId=, consumer interface=mylearning.servicecomb.constract.common.RPCService; new producer not running or not deployed.
//import mylearning.servicecomb.constract.common2.RPCService;


@RpcSchema(schemaId = "helloService")
public class HelloServiceImpl implements HelloService {
  private static final Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);

//  @RpcReference(microserviceName = "constract-provider")
//  java.lang.Error: More than one schema interface is mylearning.servicecomb.constract.service.RPCService, please use schemaId to choose a schema.
  @RpcReference(microserviceName = "constract-provider", schemaId = "RPCService")
  private RPCService provider;

  @RpcReference(microserviceName = "constract-provider", schemaId = "CommonService")
  private CommonService commonService;

  @RpcReference(microserviceName = "constract-consumer", schemaId = "helloService")
  private HelloService helloService;

  //static不会生成契约
  public Person findPerson(String name) {
    return provider.findPerson(name);
  }

  public BaseResponse<User> findUser() {
    BaseResponse<User> response = null;
    response = commonService.getUser();
    User user = (User) response.getData();
    return response;
  }

  public BaseResponse<User> findUser2() {
    BaseResponse<User> response = null;
    response = commonService.getUser2();
    User user = (User) response.getData();
    return response;
  }

  public String findUser3() {
    BaseResponse<User> response = null;
    response = commonService.getUser();
    User user = (User) response.getData();
    return "success";
  }

  public String findGender(String name){
//    若服务端没有queryGender接口契约，报错：java.lang.Error: Can not find value, name=schemaMeta RPCService operation mgr, key=queryGender
    return provider.queryGender(name);
  }

  public String findGender2(String name){
//    若消费端没有findGender接口契约，报错：java.lang.Error: Can not find value, name=schemaMeta helloService operation mgr, key=findGender
    return helloService.findGender(name);
  }

  public String uniteInvoke(String microserviceName, String schemaId, String operationName, String param) {
    LOG.info("microserviceName:{},schemaId:{},operationName:{},param:{}.", microserviceName, schemaId, operationName,
        param);
    Object[] params = {param};
    Map<String, Object> swaggerArguments = new HashMap<>();
    swaggerArguments.put("name", param);
//    String response = (String) InvokerUtils.syncInvoke(microserviceName, schemaId, operationName, swaggerArguments);
    Object response = InvokerUtils.syncInvoke(microserviceName, schemaId, operationName, params);
    return response.toString();
  }
}
