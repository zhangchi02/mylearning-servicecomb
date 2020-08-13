package mylearning.servicecomb.pojo.common.sample;

import io.swagger.annotations.ApiOperation;

public interface IUser {
  String func(String a);
  @ApiOperation(nickname="func2", value = "")
  String func(String a,int b);
}
