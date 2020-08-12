package mylearning.servicecomb.sample.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "sampleService")
@RequestMapping(path = "/sample/v0/")
public class SampleService {

  @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
  public String sayHi(@RequestParam(name = "name") String name) {
    return "Hi, " + name;
  }
}
