package mylearning.servicecomb.pojo.vertx.consumer.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.pojo.common.sample2.BusinessParam;
import mylearning.servicecomb.pojo.common.sample.PageVO;
import mylearning.servicecomb.pojo.vertx.consumer.custom.ProviderMultipleParamService;

@RpcSchema(schemaId = "multipleParamService")
public class MultipleParamService {

  @RpcReference(microserviceName = "pojovertxprovider",schemaId = "multipleParamService")
  private ProviderMultipleParamService multipleParamService;

  public List<BusinessParam> callMultipleParamService(){
    BusinessParam param = new BusinessParam();
    param.setApplicationUuid("000001");
    param.setBusinessUuid("111111");
    param.setParamId(11111111L);
    param.setParamKey("myKey");
    param.setParamValue("myValue");

    Set<String> set = new HashSet<>();
    set.add("aaa");
    set.add("bbb");
    set.add("ccc");

    PageVO page = new PageVO();
    page.setApplicationUuid(param.getApplicationUuid());
    page.setBusinessUuid(param.getBusinessUuid());

    List<BusinessParam> list = multipleParamService.findBusinessParamList(param,set,page);
    return list;
  }
}
