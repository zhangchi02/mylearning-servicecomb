package mylearning.servicecomb.springmvc.vertx.consumer.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.springmvc.vertx.consumer.demo.Hello;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2018-09-14T06:49:35.957Z")

@RestSchema(schemaId = "commonservicecall")
@RequestMapping(path = "/springmvcvertxconsumer", produces = MediaType.APPLICATION_JSON)
public class SpringMVCConsumerImpl {

  @Autowired
  private SpringMVCConsumerDelegate userSpringMVCConsumerDelegate;

  private static RestTemplate restTemplate = RestTemplateBuilder.create();

  @RpcReference(microserviceName = "springmvcvertxprovider", schemaId = "commonservice")
  private static Hello hello;

  @RequestMapping(value = "/helloworld", produces = {"application/json"}, method = RequestMethod.GET)
  public String helloworld(@RequestParam(value = "name", required = true) String name) {
    return hello.sayHello(name);
  }

  @RequestMapping(value = "/hi", produces = {"application/json"}, method = RequestMethod.GET)
  public String hi() {
    return restTemplate
        .postForEntity("cse://springmvcvertxprovider/springmvcvertxprovider/sayHello?name=Java Chassis", null, String.class)
        .getBody();
  }
}
