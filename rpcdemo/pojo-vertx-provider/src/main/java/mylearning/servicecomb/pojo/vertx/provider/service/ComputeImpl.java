package mylearning.servicecomb.pojo.vertx.provider.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.pojo.common.sample.Compute;

@RpcSchema(schemaId = "compute")
public class ComputeImpl implements Compute {
  @Override
  public int add(int a, int b) {
    return a + b;
  }

  @Override
  public int multi(int a, int b) {
    return a * b;
  }

  @Override
  public int sub(int a, int b) {
    return a - b;
  }

  @Override
  public int divide(int a, int b) {
    if (b != 0) {
      return a / b;
    }
    return 0;
  }

}
