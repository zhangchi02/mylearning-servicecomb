package mylearning.servicecomb.pojo.vertx.consumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mylearning.servicecomb.pojo.common.sample.BaseResponse;
import mylearning.servicecomb.pojo.common.sample.PermissionRpc;
import mylearning.servicecomb.pojo.common.sample.Person;
import mylearning.servicecomb.pojo.common.sample.User;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestSchema(schemaId = "permissionRpcService")
@RequestMapping(path = "/pojovertxconsumer/permission/v0")
public class PermissionRpcService {
  @RpcReference(microserviceName = "pojovertxprovider", schemaId = "permission")
  private PermissionRpc permissionRpcService;

  @GetMapping("/getUser")
  public BaseResponse<User> getUser() {
    BaseResponse<User> response = permissionRpcService.getUser();
    User user = response.getData();
    return new BaseResponse<User>(0, 0, "SUCCESS", user);
  }

  @GetMapping("/testInteger")
  public BaseResponse testInteger(@RequestParam(name = "method") String method) {
    RestTemplate restTemplate = RestTemplateBuilder.create();

    String getUrl = "cse://pojovertxprovider/IntegerImpl/testGetInteger?num=0";
    String postUrl = "cse://pojovertxprovider/PermissionRpcImpl/testPostInteger";

    Map<String, Object> map = new HashMap<>();
    map.put("integer", new Integer(0));

    ResponseEntity responseEntity = null;
    if ("post".equalsIgnoreCase(method)) {
      restTemplate.postForEntity(postUrl, new HttpEntity<>(map), ResponseEntity.class);
    } else {
      restTemplate.getForEntity(getUrl, ResponseEntity.class);
    }
//    BaseResponse user = (BaseResponse) responseEntity.getBody();
    return new BaseResponse();
  }

  @ApiOperation(value = "zhangsan", notes = "zhangsan")
  @ApiImplicitParam(paramType = "path", name = "userId", value = "用户Id", required = true, dataType = "string")
  @PostMapping(value = "/reportPosition/{userId}")
  public BaseResponse<String> reportPosition(@PathVariable("userId") String userId,
      @ApiParam(value = "lisi") @RequestBody List<Person> persons) {
    BaseResponse<String> response = new BaseResponse<String>();
    permissionRpcService.reportPosition(userId, persons);
    return response;
  }
}
