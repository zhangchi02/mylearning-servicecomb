package mylearning.servicecomb;

import java.text.SimpleDateFormat;

import org.apache.servicecomb.common.rest.codec.AbstractRestObjectMapper;
import org.apache.servicecomb.common.rest.codec.RestObjectMapper;
import org.apache.servicecomb.common.rest.codec.RestObjectMapperFactory;
import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.pagehelper.PageInfo;
import mylearning.servicecomb.common.Student;
import mylearning.servicecomb.mixin.PageInfoMixIn;
import mylearning.servicecomb.mixin.StudentMixIn;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class ProviderMain {

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
//    System.setProperty("hi.sayHiPrefix", "Welcome_${xxx}");

//    AbstractRestObjectMapper mapper = RestObjectMapperFactory.getRestObjectMapper();
//    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//    SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    mapper.setDateFormat(myDateFormat);
//    RestObjectMapperFactory.setConsumerWriterMapper(mapper);
//    RestObjectMapperFactory.setDefaultRestObjectMapper(mapper);

    Log4jUtils.init();//初始化默认的日志组件，CSEJavaSDK使用的默认日志组件是Log4J。
    BeanUtils.init();//加载Spring bean定义文件，正式开始启动流程。
  }
}
