package mylearning.servicecomb.springboot2.servlet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.servicecomb.provider.rest.common.RestSchema;

@RestSchema(schemaId = "springController100")
@RequestMapping(value = "/spring/v0")
public class SpringController100 {
  private static final Logger LOG = LoggerFactory.getLogger(SpringController.class);

  @RequestMapping(value = "/sayHello100", method = RequestMethod.GET)
  public String sayHello100(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from RestController";
  }

}
