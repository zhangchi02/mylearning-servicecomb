package com.zhc;

import java.util.Collections;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.serviceregistry.RegistryUtils;

import com.zhc.third.ThirdPartyInf;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
public class HelloWorldConsumerMain {

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
//		Runtime.getRuntime().exit(0);//退出进程，触发BEFORE_CLOSE
//		RestTemplate restTemplate = RestTemplateBuilder.create();
//		for(int i=0;i<10;i++){
//			Thread.sleep(10000);
//			restTemplate.getForEntity("cse://provider/provider/v0/hello?name=tom",String.class);
//		}
//    String endpoint = "rest://127.0.0.1:8081";
//    RegistryUtils.getServiceRegistry().registerMicroserviceMappingByEndpoints(
//        // 3rd party rest service name, you can specify the name on your need as long as you obey the microservice naming rule
//        "thirdparty",
//        // service version
//        "0.0.1",
//        // list of endpoints
//        Collections.singletonList(endpoint),
//        // java interface class to generate swagger schema
//        ThirdPartyInf.class
//    );
  }
}
