package mylearning.servicecomb.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "commonService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class CommonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

  private DynamicLongProperty helloDelay = DynamicPropertyFactory.getInstance().getLongProperty("delay.sayHello", 0);

  @RequestMapping(path = "/hello", method = {RequestMethod.GET})
  public String sayHello(@RequestParam(value = "name") String name) {
    LOGGER.info("sayHello() is invoked. name: {}.", name);
    if ("delay".equalsIgnoreCase(name)) {
      try {
        LOGGER.info("begin sleep: {}.", System.currentTimeMillis());
        Thread.sleep(helloDelay.get());
        LOGGER.info("  end sleep: {}.", System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return "Hello," + name;
  }

}
