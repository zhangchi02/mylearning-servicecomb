package mylearning.servicecomb2.springboot2.log4j2.vertx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
  private static final Logger LOG = LoggerFactory.getLogger(ProviderController.class);

  @RequestMapping(path = "/sayHi", method = RequestMethod.GET)
  public String sayHi(@RequestParam(name = "name") String name) {
    return "Hi, " + name;
  }

}
