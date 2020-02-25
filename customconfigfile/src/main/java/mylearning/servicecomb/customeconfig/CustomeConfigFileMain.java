package mylearning.servicecomb.customeconfig;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

public class CustomeConfigFileMain {
  public static void main(String[] args) throws Exception {
//    启动服务时加上java system property(servicecomb.configurationSource.defaultFileName=customconfigfile.yaml)指定使用配置文件
    Log4jUtils.init();
    BeanUtils.init();
  }
}
