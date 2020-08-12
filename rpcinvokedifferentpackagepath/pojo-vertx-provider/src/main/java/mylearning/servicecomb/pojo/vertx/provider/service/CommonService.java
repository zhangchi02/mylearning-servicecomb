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

//@RpcSchema(schemaId = "multipleParamService")
public class CommonService {

  private static final Logger LOG = LoggerFactory.getLogger(CommonService.class);

  public String sayHello(String name) {
    LOG.info("name:{}.", name);
    return "Hello, " + name;
  }
}
