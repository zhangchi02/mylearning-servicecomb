package mylearning.servicecomb2_1.exception;

import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.SwaggerInvocation;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionToProducerResponseConverter;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.servicecomb.swagger.invocation.exception.ExceptionToResponseConverter;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class GlobalExceptionConverter implements ExceptionToProducerResponseConverter<Exception> {

  private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

  public Class<Exception> getExceptionClass() {
    return Exception.class;
  }

  public Response convert(SwaggerInvocation swaggerInvocation, Exception exception) {
    LOG.info("consumer GlobalExceptionConverter.convert is invoked.");
//    StatusType status = new StatusType() {
//      @Override
//      public int getStatusCode() {
//        return 490;
//      }
//
//      @Override
//      public Family getFamily() {
//        return null;
//      }
//
//      @Override
//      public String getReasonPhrase() {
//        return "ArithmeticException exception";
//      }
//    };
//    String errorData = exception.getMessage();
//    InvocationException state = new InvocationException(status, errorData);
//    return Response.failResp(state);

    InvocationException invocationException = new InvocationException(490, "consumer global exception reasonPhrase","errorData");
    return Response.createConsumerFail(invocationException);
  }

  @Override
  public int getOrder() {
    return -100;
  }
}
