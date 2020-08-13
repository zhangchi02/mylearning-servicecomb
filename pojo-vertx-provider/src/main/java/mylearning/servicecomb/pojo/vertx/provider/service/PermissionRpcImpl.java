package mylearning.servicecomb.pojo.vertx.provider.service;

import java.util.List;

import mylearning.servicecomb.pojo.common.sample.BaseResponse;
import mylearning.servicecomb.pojo.common.sample.PermissionRpc;
import mylearning.servicecomb.pojo.common.sample.Person;
import mylearning.servicecomb.pojo.common.sample.User;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@RpcSchema(schemaId = "permission")
public class PermissionRpcImpl implements PermissionRpc {
  private static final Logger LOG = LoggerFactory.getLogger(PermissionRpcImpl.class);

  @Override
  public BaseResponse<User> getUser() {
    User user = new User("111", "222", "333", 4, "555", null);
    return new BaseResponse<>(0, 0, "SUCCESS", user);
  }

  @Override
  public BaseResponse<String> reportPosition(String userId, List<Person> persons) {
    LOG.info("userId:{},persons:{}.", userId, persons);
    BaseResponse<String> response = new BaseResponse<>();
    return response;
  }


  public BaseResponse testPostInteger(Integer integer) {
    System.out.println("testPostInteger:" + integer);
    return new BaseResponse();
  }
}
