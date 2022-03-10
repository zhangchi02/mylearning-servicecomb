package mylearning.servicecomb.service;

import java.util.List;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.constract.common.Person;
import mylearning.servicecomb.intf.RPCService;

@RestSchema(schemaId = "ConstractFirstServiceConsumer")
@RequestMapping(path = "/consumer/v0/")
public class ConstractFirstServiceConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(ConstractFirstServiceConsumer.class);

  @RpcReference(microserviceName = "constractfirst", schemaId = "RPCService")
  private RPCService rpcService;

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @RequestMapping(path = "/savePerson", method = RequestMethod.POST)
  public boolean savePerson(@RequestParam(name = "method") String method, @RequestBody Person person) {
    if ("template".equalsIgnoreCase(method)) {
      ResponseEntity<Person> responseEntity = restTemplate
          .postForEntity("cse://contractfirst/RPCServiceImpl/savePerson", person, Person.class);
      Person result = responseEntity.getBody();
      LOG.info("restTemplate return:{}.", result);
    }
    if ("rpc".equalsIgnoreCase(method)) {
      Person result = rpcService.findPerson("rpc");
      LOG.info("rpc return:{}.", result);
    }
    return true;
  }

  @RequestMapping(path = "/callSayHello", method = RequestMethod.POST)
  public boolean callSayHello() {
    ResponseEntity<String> responseEntity = restTemplate
        .getForEntity("cse://constractfirst/CommonServiceImpl/sayHello?name=java", String.class);
    return true;
  }


  @RequestMapping(path = "/findGender", method = RequestMethod.GET)
  public boolean findGender(@RequestParam(name = "name") String name) {
    String result = rpcService.queryGender(name);
    LOG.info("result return:{}.", result);
    return true;
  }

  @RequestMapping(path = "/findPersons", method = RequestMethod.GET)
  public List<Person> findPersons(@RequestParam(name = "gender") String gender) {
    List<Person> result = rpcService.listPersons(gender);
    LOG.info("result return:{}.", result);
    return result;
  }
}
