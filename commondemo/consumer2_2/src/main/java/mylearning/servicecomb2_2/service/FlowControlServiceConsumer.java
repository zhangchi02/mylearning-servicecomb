package mylearning.servicecomb2_2.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.service.FlowControlService;

@RestSchema(schemaId = "flowControlServiceConsumer")
@Path("/consumer/v0")
public class FlowControlServiceConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(FlowControlServiceConsumer.class);

  @RpcReference(microserviceName = "provider", schemaId = "flowControlService")
  private FlowControlService flowControlService;

  //RestTemplate调用方式需要创建一个ServiceComb的RestTemplate
  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @Path("/flowControl")
  @GET
  public String flowControl(@QueryParam("name") String name) {
    LOGGER.info("FlowControlServiceConsumer.flowControl");
    if ("flowControl".equalsIgnoreCase(name)) {
      while (true) {
        for (int i = 0; i < 50; i++) {
          try {
            Thread.sleep(13);
            if (i == 20) {
              restTemplate.getForEntity("cse://provider/provider/v0/flowcontrol?name=shutdown", String.class);
            }
            LOGGER.info("FlowControlServiceConsumer.flowControl: " + System.currentTimeMillis());
            restTemplate.getForEntity("cse://provider/provider/v0/flowcontrol?name=test", String.class);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return name;
  }
}
