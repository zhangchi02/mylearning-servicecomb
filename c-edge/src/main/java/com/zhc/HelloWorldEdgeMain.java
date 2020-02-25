package com.zhc;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.servicecomb.common.rest.codec.AbstractRestObjectMapper;
import org.apache.servicecomb.common.rest.codec.RestObjectMapperFactory;
import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
* @author zhangchi02
* @date 2019年3月4日
*/
public class HelloWorldEdgeMain {

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    //    System.setProperty("vertx.disableFileCPResolving", "false");

//    AbstractRestObjectMapper mapper = RestObjectMapperFactory.getRestObjectMapper();
//    SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    mapper.setDateFormat(myDateFormat);
//    mapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//    RestObjectMapperFactory.setConsumerWriterMapper(mapper);
//    RestObjectMapperFactory.setDefaultRestObjectMapper(mapper);


    Log4jUtils.init();
    BeanUtils.init();

//    RestTemplate restTemplate = RestTemplateBuilder.create();
//
//    for (int i = 0; i < 20; i++) {
//      ResponseEntity<String> responseEntity = restTemplate.getForEntity("cse://provider/provider/v0/hi/Alice",
//          String.class);
//    }
  }

}
