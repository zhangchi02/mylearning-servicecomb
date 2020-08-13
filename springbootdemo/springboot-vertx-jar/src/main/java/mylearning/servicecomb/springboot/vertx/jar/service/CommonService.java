package mylearning.servicecomb.springboot.vertx.jar.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "common")
@RequestMapping(path = "/")
public class CommonService {
  @RequestMapping(path = "hello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(name="name") String name) {
    return "Hello " + name;
  }
}
