package mylearning.servicecomb.springboot2.handler;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class DemoHandler implements Handler {
  private static final Logger LOGGER = LoggerFactory.getLogger(DemoHandler.class);

  public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
    LOGGER.info("ProviderHandler.handle is invoked.");
    //从这里可以取出本地请求调用的方法的完整名字，格式是serviceName.schemaId.operationId
    String operationName = invocation.getOperationMeta().getMicroserviceQualifiedName();
    //这里只检查sayHello方法的参数
    if ("springboot2war.common.sayHello".equals(operationName)) {
//      Object name = invocation.getSwaggerArgument("name");
      Object name = invocation.getSwaggerArgument(0);
      //如果name=stranger，则拒绝请求，返回403
      if ("stranger".equalsIgnoreCase((String) name)) {
        asyncResp.producerFail(
            new InvocationException(Status.FORBIDDEN, new CommonExceptionData("Don't know you :(")));
        return;
      }
    }
    //通过检查，继续执行后面的逻辑
    invocation.next(asyncResp);
  }
}
