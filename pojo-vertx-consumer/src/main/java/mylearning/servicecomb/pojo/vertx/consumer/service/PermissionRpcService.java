package mylearning.servicecomb.pojo.vertx.consumer.service;

import mylearning.servicecomb.pojo.common.sample.BaseResponse;
import mylearning.servicecomb.pojo.common.sample.PermissionRpc;
import mylearning.servicecomb.pojo.common.sample.User;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "permissionRpcService")
@RequestMapping(path = "/permission/v0")
public class PermissionRpcService {
  @RpcReference(microserviceName = "pojovertxprovider", schemaId = "permission")
  private PermissionRpc permissionRpcService;

  @GetMapping("/getUser")
  public BaseResponse<User> getUser() {
    BaseResponse<User> response = permissionRpcService.getUser();
    User user = response.getData();
    return new BaseResponse<User>(0, 0, "SUCCESS", user);
  }

}
