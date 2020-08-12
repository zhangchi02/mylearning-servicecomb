package mylearning.servicecomb.constract.service;


import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.constract.common.BaseResponse;
import mylearning.servicecomb.constract.common.User;

@RpcSchema(schemaId = "CommonService")
// 如果schemaId与显示契约文件名称不一致，会以当前schemaId为准生成一套契约注册到服务中心
public class CommonServiceImpl implements CommonService{
  public String sayHello() {
    return "Hello,";
  }

  public String duplicateName(String content, String queryRequestId, String headerRequestId) {
    return "success";
  }

  public BaseResponse<User> getUser(){
    User user = new User("111", "111", "111", 1, "111", null);
    return new BaseResponse<>(1, 1, "SUCCESS", user);
  }

  public BaseResponse<User> getUser2(){
    User user = new User("222", "222", "222", 2, "222", null);
    return new BaseResponse<>(2, 2, "SUCCESS", user);
  }
}
