package mylearning.servicecomb.service;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.servicecomb1servlet.SpringController;

@RestSchema(schemaId = "ServiceComb1ServletConsumer")
@RequestMapping("/consumer/v0")
public class ServiceComb1ServletConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(ServiceComb1ServletConsumer.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @RpcReference(microserviceName = "servicecomb1servlet", schemaId = "mylearning.servicecomb.springboot2.servlet.controller.SpringController")
  private SpringController controller;

  @GetMapping("/servicecomb1servlet")
  public String servicecomb1servlet(@RequestParam("method") String method, @RequestParam("param3") String param3,
      @RequestParam("param4") String param4) {
    LOG.info("servicecomb1servlet");
    String result = "";
    if ("rpc".equalsIgnoreCase(method)) {
      if ("null".equalsIgnoreCase(param3)) {
        result = controller.queryCommonDataRange("path1", "path2", null, param4);
      }
      if ("null".equalsIgnoreCase(param4)) {
        result = controller.queryCommonDataRange("path1", "path2", param3, null);
      }
      if (!"null".equalsIgnoreCase(param3) && !"null".equalsIgnoreCase(param4)) {
        result = controller.queryCommonDataRange("path1", "path2", param3, param4);
      }
    } else {
      String url = String
          .format("cse://servicecomb1servlet/spring/v0/range/%s/%s/%s/%s", "path1", "path2", param3, param4);
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
      result = response.getBody();
    }
    return result;
  }
}
