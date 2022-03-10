package mylearning.servicecomb2_2.config;

import org.apache.servicecomb.serviceregistry.config.ServiceRegistryConfig;
import org.apache.servicecomb.serviceregistry.config.ServiceRegistryConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.huawei.paas.foundation.auth.AuthHeaderProviderImpl;

//@Configuration
public class ProviderConfiguration {
//  @Bean("providerConfig")
  public ServiceRegistryConfig providerConfig() {
    ServiceRegistryConfig config = ServiceRegistryConfig.buildFromConfiguration();
//    config.setRegistryName("cn-north-1");
//    AuthHeaderProviderImpl
//    config.setAuthHeaderProviders(null);
    config = ServiceRegistryConfigCustomizer.from(config)
        .addressListFromConfiguration("cse.service.registry-north-1.address").get();
    return config;
  }
}
