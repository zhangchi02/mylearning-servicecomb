package common.simple.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "simpleService")
@RequestMapping(path = "/simple/v0/")
public class SimpleService {
  private static final Logger LOG = LoggerFactory.getLogger(SimpleService.class);

  @RequestMapping(value = "/sayOh", method = RequestMethod.GET)
  public String sayOh(@RequestParam(name = "name") String name) {
    LOG.info("sayOh is invoked.");
    return "Oh, " + name;
  }

}
