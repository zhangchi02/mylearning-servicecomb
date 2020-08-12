package mylearning.servicecomb.consumer.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
@RestSchema(schemaId = "commonServiceConsumer")
@Path("/consumer/v0") //这里使用JAX-PS风格开发consumer服务
public class CommonServiceConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceConsumer.class);

  //RestTemplate调用方式需要创建一个ServiceComb的RestTemplate
  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @Path("/hello")
  @GET
  public String sayHello(@QueryParam("name") String name) {
    ResponseEntity<String> response = restTemplate
        .getForEntity("cse://servicecomb-provider/provider/v0/hello?name=servicecomb", String.class);
    return response.getBody();
  }
}
