package mylearning.servicecomb.pojo.vertx.consumer.service;

import org.apache.servicecomb.provider.pojo.Invoker;
import org.springframework.stereotype.Component;

import mylearning.servicecomb.pojo.common.sample.Compute;

@Component
public class ComputeService {

  public static int add(int a, int b) {
    Compute compute = Invoker.createProxy("provider", "compute", Compute.class);
    return compute.add(a, b);
  }
}
