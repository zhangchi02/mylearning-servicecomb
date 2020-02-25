package mylearning.servicecomb.customeconfig.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "common")
@RequestMapping("/")
public class CommonService {
  @GetMapping(path = "/sayHello")
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name;
  }
}
