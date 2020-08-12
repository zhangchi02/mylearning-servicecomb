package mylearning.servicecomb;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

public class HelloWorldProviderMain {
  public static void main(String[] args) throws Exception {
    System.setProperty("archaius.configuration.disableDelimiterParsing", "true");

    Log4jUtils.init();//初始化默认的日志组件，CSEJavaSDK使用的默认日志组件是Log4J。
    BeanUtils.init();//加载Spring bean定义文件，正式开始启动流程。
  }
}
