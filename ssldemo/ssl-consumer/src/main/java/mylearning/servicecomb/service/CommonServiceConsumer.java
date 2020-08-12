package mylearning.servicecomb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;

import mylearning.servicecomb.provider.service.ICommonService;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
@RestSchema(schemaId = "commonServiceConsumer")
@Path("/consumer/v0") //这里使用JAX-PS风格开发consumer服务
public class CommonServiceConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceConsumer.class);

  //RPC调用方式需要声明一个provider服务的REST借口代理
  @RpcReference(microserviceName = "sslprovider", schemaId = "commonService")
//  @RpcReference(microserviceName = "provider")
//  Schema not exist, microserviceName=provider, schemaId=, consumer interface=mylearning.servicecomb.service.ICommonService; new producer not running or not deployed.
  private ICommonService commonService;

  //RestTemplate调用方式需要创建一个ServiceComb的RestTemplate
  private RestTemplate restTemplate = RestTemplateBuilder.create();

  private DynamicLongProperty helloDelay = DynamicPropertyFactory.getInstance().getLongProperty("delay.hello", 0);

  @Path("/hello")
  @GET
  public String hello(@QueryParam("name") String name) {
    if (helloDelay.get() > 0) {
      try {
        LOGGER.info("begin sleep: {}.", System.currentTimeMillis());
        Thread.currentThread().sleep(helloDelay.get());
        LOGGER.info("  end sleep: {}.", System.currentTimeMillis());
      } catch (InterruptedException e) {
        LOGGER.error("sayHello sleeping is interrupted!", e);
      }
    }
    //RPC调用方式体验与本地调用相同
    String response = commonService.sayHello(name);
    LOGGER.info("get response: {}", response);
    return response;
  }

}
