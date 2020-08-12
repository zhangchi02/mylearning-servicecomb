package mylearning.servicecomb.consumer;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
public class HelloWorldConsumerMain {

  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
  }
}
