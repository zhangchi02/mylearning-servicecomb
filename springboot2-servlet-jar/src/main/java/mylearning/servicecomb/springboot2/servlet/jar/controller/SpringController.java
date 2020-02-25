package mylearning.servicecomb.springboot2.servlet.jar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spring/v0")
public class SpringController {
  @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name;
  }
}
