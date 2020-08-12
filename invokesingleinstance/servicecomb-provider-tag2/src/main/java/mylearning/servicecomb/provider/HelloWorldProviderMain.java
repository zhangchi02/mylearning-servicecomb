package mylearning.servicecomb.provider;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class HelloWorldProviderMain {

  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
  }
}
