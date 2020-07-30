package mylearning.servicecomb.springboot2.servlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/controller/v0")
public class TestController {
  @RequestMapping(value = "/testController",method = RequestMethod.GET)
  public String testController(@RequestParam(name = "name") String name) {
    return "testController, " + name + " , from controller";
  }
}
