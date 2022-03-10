package mylearning.servicecomb2.service;

import mylearning.servicecomb.common.GreetingResponse;
import mylearning.servicecomb.common.Person;
import mylearning.servicecomb.common.Teacher;
import org.apache.servicecomb.core.provider.consumer.InvokerUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.CseHttpEntity;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.provider.springmvc.reference.async.CseAsyncRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestSchema(schemaId = "exceptionService")
@RequestMapping(path = "/consumer/v0")
public class ExceptionService {
  private static final Logger LOG = LoggerFactory.getLogger(ExceptionService.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  CseAsyncRestTemplate cseAsyncRestTemplate = new CseAsyncRestTemplate();

  @GetMapping("/callGreeting")
  public GreetingResponse callGreeting() {
    String url = "cse://provider/provider/v0/greeting";
    Person person = new Person("zhangsan","male");
    ResponseEntity<GreetingResponse> response = restTemplate.postForEntity(url,person,GreetingResponse.class);
    GreetingResponse result = response.getBody();
    return result;
  }

}
