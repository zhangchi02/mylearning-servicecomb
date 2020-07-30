package mylearning.servicecomb2.springboot2.servlet.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestSchema(schemaId = "CommonController")
@RequestMapping(path = "/servicecomb2servlet/v0")
public class CommonController {
  private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from commonService";
  }

}
