package mylearning.servicecomb.pojo.vertx.consumer.custom;

import java.util.List;
import java.util.Set;

import mylearning.servicecomb.pojo.common.sample2.BusinessParam;
import mylearning.servicecomb.pojo.common.sample.PageVO;

public interface ProviderMultipleParamService {
  List<BusinessParam> findBusinessParamList(BusinessParam param, Set<String> permitApps, PageVO page);
}
