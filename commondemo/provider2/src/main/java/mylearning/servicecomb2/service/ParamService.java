package mylearning.servicecomb2.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import mylearning.servicecomb.common.Gender;
import mylearning.servicecomb.common.GreetingResponse;
import mylearning.servicecomb.common.Person;
import mylearning.servicecomb.common.Student;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "paramService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class ParamService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ParamService.class);

  //从配置项获取(从云端下发需要注销microservice.yaml中的environment=development配置)
  private DynamicStringProperty sayHiPrefix =
      DynamicPropertyFactory.getInstance().getStringProperty("hi.sayHiPrefix", "", notifyConfigRefreshed());

  private DynamicStringProperty redisHost =
      DynamicPropertyFactory.getInstance().getStringProperty("commons.redis.host", "", notifyConfigRefreshed());

  private DynamicStringProperty redisPort =
      DynamicPropertyFactory.getInstance().getStringProperty("commons.redis.port", "", notifyConfigRefreshed());

  private DynamicStringProperty redisHost1 =
      DynamicPropertyFactory.getInstance().getStringProperty("commons.redis.host1", "", notifyConfigRefreshed());

  private DynamicStringProperty redisMaxIdle =
      DynamicPropertyFactory.getInstance().getStringProperty("commons.redis.maxIdle", "", notifyConfigRefreshed());

  private DynamicStringProperty key3 =
      DynamicPropertyFactory.getInstance().getStringProperty("key3", "", notifyConfigRefreshed());

  private Runnable notifyConfigRefreshed() {
    return () -> {
      LOGGER.info("config[hi.sayHiPrefix] changed to [{}]!", sayHiPrefix.getValue());
      LOGGER.info("config[commons.redis.host] changed to [{}]!", redisHost.getValue());
      LOGGER.info("config[commons.redis.port] changed to [{}]!", redisPort.getValue());
      LOGGER.info("config[commons.redis.host1] changed to [{}]!", redisHost1.getValue());
      LOGGER.info("config[commons.redis.maxIdle] changed to [{}]!", redisMaxIdle.getValue());
      LOGGER.info("config[key3] changed to [{}]!", key3.getValue());
    };
  }

  @RequestMapping(path = "/hi/{name}", method = RequestMethod.GET)
  public String sayHi(@PathVariable(value = "name") String name) {
    return sayHiPrefix.getValue() + name;
  }
}
