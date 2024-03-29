package mylearning.servicecomb2_1.exception;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.SwaggerInvocation;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionToProducerResponseConverter;
//import org.apache.servicecomb.swagger.invocation.exception.ExceptionToResponseConverter;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class IllegalArgumentExceptionConverter implements ExceptionToProducerResponseConverter<IllegalArgumentException> {
  private static final Logger LOGGER = LoggerFactory.getLogger(IllegalArgumentExceptionConverter.class);
  /**
   * 当业务代码抛出的IllegalArgumentException被捕获后，会传入该方法进行处理。
   * @param swaggerInvocation 本次业务调用相关的信息
   * @param e 被捕获的异常
   * @return 转换后的响应信息，将会发送给调用方
   */
  public Response convert(SwaggerInvocation swaggerInvocation, IllegalArgumentException e) {
    LOGGER.info("IllegalArgumentExceptionConverter.convert is invoked.");
    return Response.consumerFailResp(new InvocationException(Status.BAD_REQUEST, new CommonExceptionData(
        swaggerInvocation.getInvocationQualifiedName() + " gets illegal param: " + e.getMessage())));
  }

  /**
   * 该方法的返回值表明ProviderExceptionConverter处理IllegalArgumentException及其子类的异常
   */
  public Class<IllegalArgumentException> getExceptionClass() {
    return IllegalArgumentException.class;
  }

//  public int getOrder() {
//    return -100;
//  }

}
