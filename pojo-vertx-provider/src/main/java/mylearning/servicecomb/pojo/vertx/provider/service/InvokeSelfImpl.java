package mylearning.servicecomb.pojo.vertx.provider.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mylearning.servicecomb.pojo.common.sample.BaseResponse;
import mylearning.servicecomb.pojo.common.sample.Header;

@RestSchema(schemaId = "invokeSelf")
@RequestMapping("/InvokeSelfImpl")
public class InvokeSelfImpl {

  @RpcReference(microserviceName = "pojovertxprovider", schemaId = "invokeSelf")
  private Header headerimpl;

  @GetMapping("/invokeSelf")
  public String invokeSelf(String method) {
    String result = null;
    if ("checkHeader".equalsIgnoreCase(method)) {
      result = headerimpl.checkHeader("zhangsan", "00001", 28, "male");
    } else {
      headerimpl.withoutParam("withoutParam");
    }
    return result;
  }
}
