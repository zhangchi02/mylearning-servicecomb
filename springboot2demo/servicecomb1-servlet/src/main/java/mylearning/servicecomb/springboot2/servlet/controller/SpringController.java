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

@RestSchema(schemaId = "springController")
@RequestMapping(value = "/spring/v0")
public class SpringController {
  private static final Logger LOG = LoggerFactory.getLogger(SpringController.class);

  @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from RestController";
  }

  @RequestMapping(value = "/range/{pathVariable1}/{pathVariable2}/{pathVariable3}/{pathVariable4}", method = RequestMethod.GET)
  public String queryCommonDataRange(@PathVariable(name = "pathVariable1") String pathVariable1,
      @PathVariable(name = "pathVariable2") String pathVariable2,
      @PathVariable(name = "pathVariable3") String pathVariable3,
      @PathVariable(name = "pathVariable4", required = false) String pathVariable4) {
    LOG.info("pathVariable1: {},pathVariable2: {},pathVariable3: {},pathVariable4: {}.", pathVariable1, pathVariable2,
        pathVariable3, pathVariable4);
    return "success";
  }

  @GetMapping("paramValidate")
  public String paramValidate(
      @Valid @Pattern(regexp = "^[a-fA-F0-9]{24}$") @RequestParam(value = "marker", defaultValue = "ffffffffffffffffffffffff", required = false) String marker,
      @RequestParam("offset") String offset) {
    LOG.info("marker:{},offset:{}.", marker, offset);
    return "success";
  }
}
