package mylearning.servicecomb.log4j2.service;

import javax.ws.rs.core.MediaType;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "hello")
@RequestMapping(path = "/server")
public interface Hello {
  @RequestMapping(path = "/value",produces = MediaType.APPLICATION_JSON)
  String sayHello(@RequestParam(name="name") String name);
}
