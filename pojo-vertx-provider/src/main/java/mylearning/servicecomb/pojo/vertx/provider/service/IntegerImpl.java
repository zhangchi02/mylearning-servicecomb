package mylearning.servicecomb.pojo.vertx.provider.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mylearning.servicecomb.pojo.common.sample.BaseResponse;

@RestSchema(schemaId = "integer")
@RequestMapping("/IntegerImpl")
public class IntegerImpl {

  @GetMapping("/testGetInteger")
  public BaseResponse testInteger(@RequestParam(name = "num") Integer integer) {
    System.out.println("testInteger:" + integer);
    return new BaseResponse();
  }
}
