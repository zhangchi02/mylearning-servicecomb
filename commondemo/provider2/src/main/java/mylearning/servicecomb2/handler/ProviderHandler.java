package mylearning.servicecomb2.handler;

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
public class ProviderHandler implements Handler {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProviderHandler.class);

  /*
   * Handler机制工作于用户业务代码接受REST请求之前和发送REST请求之后，支持默认/服务两个级别的配置
   * public interface Handler{
   */

  /**
   * 每次有请求经过handler链时，都会被这个方法处理一次
   * @param invocation invocation中记录了本地请求相关的信息
   * @param asyncResp asyncResp用于异步返回处理结果
   * @throws Exception
   *//*
            void handle(Invocation invocation,AsyncResponse asyncResp) throws Exception;
            }*/
  public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
    LOGGER.info("ProviderHandler.handle is invoked.");
    //从这里可以取出本地请求调用的方法的完整名字，格式是serviceName.schemaId.operationId
    String operationName = invocation.getOperationMeta().getMicroserviceQualifiedName();
    //这里只检查sayHello方法的参数
    if ("provider.commonService.sayHello".equals(operationName)) {
      Object name = invocation.getSwaggerArgument("name");
//      Object name = invocation.getSwaggerArgument(0);
      //如果name=stranger，则拒绝请求，返回403
      if (!"true".equalsIgnoreCase(invocation.getContext("LetStrangerPass"))
          && "stranger".equalsIgnoreCase((String) name)) {
        asyncResp.producerFail(
            new InvocationException(Status.FORBIDDEN, new CommonExceptionData("Don't know you :(")));
        return;
      }
    }
    if ("provider.commonService.greeting".equals(operationName)) {
      LOGGER.info("ProviderHandler handle:greeting");
      asyncResp.producerFail(
          new InvocationException(Status.INTERNAL_SERVER_ERROR, new CommonExceptionData("handler null:greeting")));
      return;
    }
//    if ("provider.hiService.logoff".equals(operationName)) {
//      LOGGER.info("ProviderHandler handle:logoff");
//      asyncResp.producerFail(
//          new InvocationException(Status.INTERNAL_SERVER_ERROR, new CommonExceptionData("handler null:logoff")));
//      return;
//    }
    //通过检查，继续执行后面的逻辑
    invocation.next(asyncResp);
  }
}
