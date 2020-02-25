package com.zhc.service;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhc.third.ThirdPartyInf;

@RestSchema(schemaId = "thirdPartyServiceConsumer")
public class ThirdPartyServiceConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyServiceConsumer.class);
  // declare rpc reference to 3rd party rest service, schemaId is the same as microservice name
  @RpcReference(microserviceName = "thirdparty", schemaId = "thirdparty")
  ThirdPartyInf commonServerIntf;

  @RequestMapping(path = "/third", method = RequestMethod.GET)
  public String thirdInvoke(@RequestParam(name = "name") String name) {
    LOGGER.info("thirdInvoke() is called, name = [{}]", name);
    // invoke 3rd party rest service
    String response = commonServerIntf.sayHello(name);
    LOGGER.info("sayHello() response = [{}]", response);
    return response;
  }
}
