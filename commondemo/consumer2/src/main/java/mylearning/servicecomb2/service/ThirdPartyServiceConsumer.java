package mylearning.servicecomb2.service;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.CseHttpEntity;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb2.third.ThirdPartyInf;

import mylearning.springcloud.springboot2webappjar.pojo.Person;

@RestSchema(schemaId = "thirdPartyServiceConsumer")
public class ThirdPartyServiceConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyServiceConsumer.class);

  // declare rpc reference to 3rd party rest service, schemaId is the same as microservice name
  @RpcReference(microserviceName = "thirdparty", schemaId = "thirdparty")
  ThirdPartyInf commonServerIntf;

  @RequestMapping(path = "/thirdSayHello", method = RequestMethod.GET)
  public String thirdSayHello(@RequestParam(name = "name") String name) {
    LOGGER.info("thirdSayHello() is called, name = [{}]", name);
    // invoke 3rd party rest service
    String response = commonServerIntf.sayHello(name);
    LOGGER.info("sayHello() response = [{}]", response);
    return response;
  }

  @RequestMapping(path = "/thirdPerson", method = RequestMethod.GET)
  public Person thirdPerson() {
    LOGGER.info("thirdPerson() is called.");
    RestTemplate restTemplate = RestTemplateBuilder.create();
    String url = "cse://thirdparty/common/person";

    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add("myHeader", "JavaChassis");

    Person person = new Person();
    person.setAge(23);
    person.setName("Java");
    HttpEntity<Person> entity = new HttpEntity<>(person);

    ResponseEntity responseEntity = restTemplate
        .postForEntity(url, new CseHttpEntity<>(person, headers), ResponseEntity.class);
    Person response = (Person) responseEntity.getBody();
    LOGGER.info("response = [{}]", response);
    return response;
  }
}
