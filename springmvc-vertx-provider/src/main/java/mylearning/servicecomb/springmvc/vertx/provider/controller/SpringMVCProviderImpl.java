package mylearning.servicecomb.springmvc.vertx.provider.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mylearning.servicecomb.springmvc.vertx.provider.demo.Hello;

import org.apache.servicecomb.provider.rest.common.RestSchema;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2018-09-14T06:40:35.282Z")

@RestSchema(schemaId = "commonservice")
@RequestMapping(path = "/springmvcvertxprovider", produces = MediaType.APPLICATION_JSON)
public class SpringMVCProviderImpl implements Hello {

  @Autowired
  private SpringMVCProviderDelegate userSpringMVCProviderDelegate;

  @RequestMapping(value = "/helloworld", produces = {"application/json"}, method = RequestMethod.GET)
  public String helloworld(@RequestParam(value = "name", required = true) String name) {

    return userSpringMVCProviderDelegate.helloworld(name);
  }

  @Override
  @RequestMapping(value = "/sayHello", produces = {"application/json"}, method = RequestMethod.POST)
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
