package mylearning.servicecomb.pojo.vertx.consumer.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.pojo.common.sample.BusinessParam;
import mylearning.servicecomb.pojo.common.sample.PageVO;
import mylearning.servicecomb.pojo.common.sample2.BusinessParam2;
import mylearning.servicecomb.pojo.vertx.consumer.custom.IMultipleParamService;

@RpcSchema(schemaId = "multipleParamService")
public class MultipleParamService {

  private static final Logger LOG = LoggerFactory.getLogger(MultipleParamService.class);

  @RpcReference(microserviceName = "differentpathprovider", schemaId = "multipleParamService")
  private IMultipleParamService multipleParamService;

  public List<BusinessParam> callParamList() {
    List<BusinessParam> list = multipleParamService.findBusinessParamList(param, set, page);
    LOG.info("list:{}.", list);
    return list;
  }

  public List<BusinessParam2> callParam2List() {
    List<BusinessParam2> list = multipleParamService.findBusinessParam2List(param2, set, page);
    LOG.info("list:{}.", list);
    return list;
  }

  public BusinessParam callParam() {
    BusinessParam response = multipleParamService.findBusinessParam(param);
    LOG.info("response:{}.", response);
    return response;
  }

  public BusinessParam2 callParam2() {
    BusinessParam2 response = multipleParamService.findBusinessParam2(param2);
    LOG.info("response:{}.", response);
    return response;
  }

  private static BusinessParam param = new BusinessParam();

  private static BusinessParam2 param2 = new BusinessParam2();

  private static Set<String> set = new HashSet<>();

  private static PageVO page = new PageVO();

  static {
    param.setApplicationUuid("000001");
    param.setBusinessUuid("111111");
    param.setParamId(11111111L);
    param.setParamKey("myKey");
    param.setParamValue("myValue");

    param2.setApplicationUuid("000002");
    param2.setBusinessUuid("222222");
    param2.setParamId(22222222L);
    param2.setParamKey("myKey2");
    param2.setParamValue("myValue2");

    set.add("aaa");
    set.add("bbb");
    set.add("ccc");

    page.setApplicationUuid(param.getApplicationUuid());
    page.setBusinessUuid(param.getBusinessUuid());
  }
}
