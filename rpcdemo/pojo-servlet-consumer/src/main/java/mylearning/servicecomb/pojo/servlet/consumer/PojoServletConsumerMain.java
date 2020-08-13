package mylearning.servicecomb.pojo.servlet.consumer;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

import mylearning.servicecomb.pojo.servlet.consumer.service.HelloService;

public class PojoServletConsumerMain {

  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
  }

}
