package mylearning.servicecomb.contract.service;


import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.contract.common.Person;

@RpcSchema(schemaId = "CommonService")
// 如果schemaId与显示契约文件名称不一致，会以当前schemaId为准生成一套契约注册到服务中心
public class CommonServiceImpl {

  @RpcReference(microserviceName = "constractfirst", schemaId = "RPCService")
  private RPCService rpcService;

  public String sayHello() {
    return "Hello,";
  }

  public String duplicateName(String content, String queryRequestId, String headerRequestId) {
    return "success";
  }

  public String callQueryGender(String name, Person person) {
    String gender = rpcService.queryGender(name, person);
//    String gender = rpcService.queryGender(name);
    return gender;
  }
}
