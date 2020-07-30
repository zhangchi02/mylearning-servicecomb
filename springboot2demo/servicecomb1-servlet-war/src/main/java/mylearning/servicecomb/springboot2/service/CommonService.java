package mylearning.servicecomb.springboot2.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "CommonService")
@RequestMapping(path = "/")
public class CommonService {
  @RequestMapping(path = "sayHello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(name="name") String name) {
    return "Hello " + name;
  }
}
