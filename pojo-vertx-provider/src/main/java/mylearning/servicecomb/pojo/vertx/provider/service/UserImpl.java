package mylearning.servicecomb.pojo.vertx.provider.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.pojo.common.sample.IUser;

import io.swagger.annotations.ApiOperation;

@RpcSchema(schemaId = "user")
public class UserImpl implements IUser {

  @Override
  public String func(String a) {
    return a;
  }

  @ApiOperation(nickname="func2", value = "")
  @Override
  public String func(String a, int b) {
    return a + ":" + b;
  }

}
