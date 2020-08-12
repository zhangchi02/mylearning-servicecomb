package mylearning.servicecomb.service;

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
}
