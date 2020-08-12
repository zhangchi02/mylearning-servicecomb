package mylearning.servicecomb.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "CommonService")
@RequestMapping(path = "/provider/v0")
public class CommonService {

  @RequestMapping(path = "/hello", method = {RequestMethod.GET})
  public String sayHello(@RequestParam(value = "name") String name) {
    return "Hello," + name;
  }

  @RequestMapping(path = "/hi", method = {RequestMethod.GET})
  public String sayHi(@RequestParam(value = "name") String name) {
    return "Hi," + name;
  }
}
