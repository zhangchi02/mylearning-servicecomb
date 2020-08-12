package mylearning.servicecomb.contract.service;


import org.apache.servicecomb.provider.pojo.RpcSchema;

@RpcSchema(schemaId = "FromDependency")
public class FromDependencyServiceImpl {
  public String thirdUrl(String name) {
    return "Hello," + name;
  }
}
