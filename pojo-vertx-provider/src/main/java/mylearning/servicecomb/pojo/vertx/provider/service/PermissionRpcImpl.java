package mylearning.servicecomb.pojo.vertx.provider.service;

import mylearning.servicecomb.pojo.common.sample.BaseResponse;
import mylearning.servicecomb.pojo.common.sample.PermissionRpc;
import mylearning.servicecomb.pojo.common.sample.User;
import org.apache.servicecomb.provider.pojo.RpcSchema;

@RpcSchema(schemaId = "permission")
public class PermissionRpcImpl implements PermissionRpc {
  @Override
  public BaseResponse<User> getUser() {
    User user = new User("111", "222", "333", 4, "555", null);
    return new BaseResponse<>(0, 0, "SUCCESS", user);
  }
}
