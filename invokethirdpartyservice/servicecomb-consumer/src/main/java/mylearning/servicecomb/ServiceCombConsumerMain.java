package mylearning.servicecomb;

import java.util.Collections;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.serviceregistry.RegistryUtils;

import mylearning.servicecomb.third.ThirdPartyInf;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
public class ServiceCombConsumerMain {

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();

    String endpoint = "rest://127.0.0.1:8081";
    RegistryUtils.getServiceRegistry().registerMicroserviceMappingByEndpoints(
        // 3rd party rest service name, you can specify the name on your need as long as you obey the microservice naming rule
        "thirdparty",
        // service version
        "0.0.1",
        // list of endpoints
        Collections.singletonList(endpoint),
        // java interface class to generate swagger schema
        ThirdPartyInf.class
    );
  }
}
