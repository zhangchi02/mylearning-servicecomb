package mylearning.servicecomb.pojo.vertx.provider.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.pojo.common.sample.Person;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RpcSchema(schemaId = "header")
public class HeaderImpl {

  private static final Logger LOG = LoggerFactory.getLogger(HeaderImpl.class);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "name", value = "姓名", dataType = "string", paramType = "header"),
      @ApiImplicitParam(name = "id", value = "id", dataType = "string", paramType = "header"),
      @ApiImplicitParam(name = "age", value = "年龄", dataType = "integer", paramType = "query"),
      @ApiImplicitParam(name = "gender", value = "性别", dataType = "string", paramType = "query")
  })
  public String checkHeader(String name, String id, Integer age, String gender) {
    LOG.info("checkHeader is invoked, name:{}, id:{}, age:{}, gender:{}.", name, id, age, gender);
    return "Hello " + name;
  }

  public String sayHello(Person person) {
    return "Hello person " + person.getName();
  }
}
