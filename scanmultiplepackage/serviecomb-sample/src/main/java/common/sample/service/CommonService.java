package common.sample.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "commonService")
@RequestMapping(path = "/sample/v0/")
public class CommonService {

  @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name;
  }
}
