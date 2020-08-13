package mylearning.servicecomb.pojo.vertx.consumer.service;

import org.apache.servicecomb.provider.pojo.Invoker;
import org.springframework.stereotype.Component;

import mylearning.servicecomb.pojo.common.sample.IUser;

@Component
public class UserService {

  public static String func(String a, int b) {
    IUser user = Invoker.createProxy("provider", "user", IUser.class);
    return user.func(a, b);
  }
}
