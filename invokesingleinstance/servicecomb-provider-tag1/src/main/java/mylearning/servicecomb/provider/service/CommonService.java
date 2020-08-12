package mylearning.servicecomb.provider.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "commonService")
@RequestMapping(path = "/provider/v0")
public class CommonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

  @RequestMapping(path = "/hello", method = {RequestMethod.GET})
  public String sayHello(@RequestParam(value = "name") String name) {
    return "Hello," + name + "from instance1.";
  }
}
