package mylearning.servicecomb.pojo.vertx.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mylearning.servicecomb.pojo.common.sample.BusinessParam;
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
}
