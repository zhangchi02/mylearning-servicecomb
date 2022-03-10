package mylearning.servicecomb2.service.constractfirst;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.common.Person;

@RestSchema(schemaId = "rpcConsumer")
@RequestMapping(path = "/consumer/v0/")
public class RPCConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(RPCConsumer.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @RequestMapping(path = "/savePerson", method = RequestMethod.POST)
  public boolean savePerson(@RequestBody Person person) {
    ResponseEntity<Person> responseEntity = restTemplate
        .postForEntity("cse://contractfirst/RPCServiceImpl/savePerson", person, Person.class);
    return true;
  }
}
