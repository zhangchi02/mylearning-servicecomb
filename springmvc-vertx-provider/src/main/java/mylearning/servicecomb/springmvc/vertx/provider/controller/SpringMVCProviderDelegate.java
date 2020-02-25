package mylearning.servicecomb.springmvc.vertx.provider.controller;

import org.springframework.stereotype.Component;


@Component
public class SpringMVCProviderDelegate {

  public String helloworld(String name) {
    // Do Some Magic Here!
    return name;
  }
}
