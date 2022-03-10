package mylearning.servicecomb.springboot2.servlet.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "springController101")
@RequestMapping(value = "/spring/v0")
public class SpringController101 {
  private static final Logger LOG = LoggerFactory.getLogger(SpringController.class);

  @RequestMapping(value = "/sayHello101", method = RequestMethod.GET)
  public String sayHello101(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from RestController";
  }

}
