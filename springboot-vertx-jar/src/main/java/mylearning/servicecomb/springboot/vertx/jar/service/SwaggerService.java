package mylearning.servicecomb.springboot.vertx.jar.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestSchema(schemaId = "swagger")
@RequestMapping(path = "/")
public class SwaggerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerService.class);

  @RequestMapping(path = "/annotation", method = RequestMethod.GET)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "operator", value = "撤销人id", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "name", value = "操作人姓名", dataType = "string", paramType = "query")
  })
  public String query(String id, String operator, String name) {
    LOGGER.info("query() is invoked, id: {}, operator: {}, name: {}.", id, operator, name);
    return "Hello " + name;
  }
}
