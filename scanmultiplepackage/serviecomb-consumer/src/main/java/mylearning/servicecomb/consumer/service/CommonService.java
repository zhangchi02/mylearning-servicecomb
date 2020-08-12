package mylearning.servicecomb.consumer.service;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.sample.service.ISampleService;

@RestSchema(schemaId = "commonService")
@RequestMapping(path = "/consumer/v0/")
public class CommonService {
  private static final Logger LOG = LoggerFactory.getLogger(CommonService.class);

  @RpcReference(microserviceName = "servicecomb-sample", schemaId = "sampleService")
  private ISampleService sampleService;

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello(@RequestParam(name = "name") String name) {
    ResponseEntity<String> response = restTemplate
        .getForEntity("cse://servicecomb-sample/sample/v0/sayHello?name=" + name, String.class);
    String result = response.getBody();
    return result;
  }

  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  public String hi(@RequestParam(name = "name") String name) {
    String result = sampleService.sayHi(name);
    return result;
  }
}
