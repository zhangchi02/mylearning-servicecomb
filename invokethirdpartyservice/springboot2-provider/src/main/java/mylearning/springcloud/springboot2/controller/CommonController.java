package mylearning.springcloud.springboot2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mylearning.springcloud.springboot2.pojo.Person;

@RestController
@RequestMapping(value = "/common")
public class CommonController {
  private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

  @RequestMapping(value = "/sayHello")
  public String sayHello(@RequestParam(name = "name") String name) {
    LOGGER.info("name: {}.", name);
    return "Hello, " + name;
  }

  @RequestMapping(value = "/person", method = RequestMethod.POST)
  public Person person(@RequestHeader("myHeader") String myHeader, @RequestBody Person person) {
    LOGGER.info("myHeader: {}, person: {}.", myHeader, person);
    return person;
  }
}
