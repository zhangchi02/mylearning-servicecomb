package mylearning.servicecomb2.springboot2.log4j2.vertx.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "ConstractController")
public class ConstractController {
  private static final Logger LOG = LoggerFactory.getLogger(ConstractController.class);

  public String constract(@RequestParam(name = "name") String name) {
    return "constract, " + name;
  }
}
