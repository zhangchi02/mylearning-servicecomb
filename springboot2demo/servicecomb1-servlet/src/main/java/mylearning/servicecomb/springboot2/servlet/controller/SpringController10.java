package mylearning.servicecomb.springboot2.servlet.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.PathParam;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestSchema(schemaId = "springController10")
@RequestMapping(value = "/spring/v0")
public class SpringController10 {
  private static final Logger LOG = LoggerFactory.getLogger(SpringController.class);

  @RequestMapping(value = "/sayHello10", method = RequestMethod.GET)
  public String sayHello10(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from RestController";
  }

}
