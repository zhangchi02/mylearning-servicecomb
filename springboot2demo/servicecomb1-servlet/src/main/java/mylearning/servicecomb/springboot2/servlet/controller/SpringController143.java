package mylearning.servicecomb.springboot2.servlet.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.servicecomb.provider.rest.common.RestSchema;

@RestSchema(schemaId = "springController143")
@RequestMapping(value = "/spring/v0")
public class SpringController143 {
  private static final Logger LOG = LoggerFactory.getLogger(SpringController.class);

  @RequestMapping(value = "/sayHello143", method = RequestMethod.GET)
  public String sayHello143(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from RestController";
  }

}
