package mylearning.servicecomb.pojo.vertx.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.pojo.common.sample.BusinessParam;
import mylearning.servicecomb.pojo.common.sample.BusinessParam2;
import mylearning.servicecomb.pojo.common.sample.PageVO;

@RpcSchema(schemaId = "multipleParamService")
public class MultipleParamService {

  private static final Logger LOG = LoggerFactory.getLogger(MultipleParamService.class);

  public List<BusinessParam> findBusinessParamList(BusinessParam param, Set<String> permitApps, PageVO page) {
    LOG.info("param:{},permitApps:{},page:{}.", param, permitApps, page);
    List<BusinessParam> list = new ArrayList<>();
    list.add(param);

    return list;
  }

  public List<BusinessParam2> findBusinessParam2List(BusinessParam2 param2, Set<String> permitApps, PageVO page) {
    LOG.info("param2:{},permitApps:{},page:{}.", param2, permitApps, page);
    List<BusinessParam2> list = new ArrayList<>();
    list.add(param2);

    return list;
  }

  public BusinessParam findBusinessParam(BusinessParam param) {
    LOG.info("param:{}.", param);
    return param;
  }

  public BusinessParam2 findBusinessParam2(BusinessParam2 param2) {
    LOG.info("param2:{}.", param2);
    return param2;
  }
}
