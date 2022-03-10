package mylearning.servicecomb2_2.exception;

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
public class ArithmeticExceptionConverter implements ExceptionToProducerResponseConverter<ArithmeticException> {

  private static final Logger LOG = LoggerFactory.getLogger(ArithmeticExceptionConverter.class);

  public Class<ArithmeticException> getExceptionClass() {
    return ArithmeticException.class;
  }

  public Response convert(SwaggerInvocation swaggerInvocation, ArithmeticException arithmeticException) {
    LOG.info("ArithmeticExceptionConverter.convert is invoked.");
    StatusType status = new StatusType() {
      @Override
      public int getStatusCode() {
        return 490;
      }

      @Override
      public Family getFamily() {
        return null;
      }

      @Override
      public String getReasonPhrase() {
        return "ArithmeticException exception";
      }
    };
    String errorData = arithmeticException.getMessage();
    InvocationException state = new InvocationException(status, errorData);
    return Response.failResp(state);
  }
}
