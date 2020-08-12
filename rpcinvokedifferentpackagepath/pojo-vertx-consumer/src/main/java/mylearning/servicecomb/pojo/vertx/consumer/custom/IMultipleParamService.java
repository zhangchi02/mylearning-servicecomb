package mylearning.servicecomb.pojo.vertx.consumer.custom;

import java.util.List;
import java.util.Set;

import mylearning.servicecomb.pojo.common.sample.BusinessParam;
import mylearning.servicecomb.pojo.common.sample.PageVO;
import mylearning.servicecomb.pojo.common.sample2.BusinessParam2;

public interface IMultipleParamService {
  List<BusinessParam> findBusinessParamList(BusinessParam param, Set<String> permitApps, PageVO page);

  List<BusinessParam2> findBusinessParam2List(BusinessParam2 param2, Set<String> permitApps, PageVO page);

  BusinessParam findBusinessParam(BusinessParam param);

  BusinessParam2 findBusinessParam2(BusinessParam2 param2);
}
