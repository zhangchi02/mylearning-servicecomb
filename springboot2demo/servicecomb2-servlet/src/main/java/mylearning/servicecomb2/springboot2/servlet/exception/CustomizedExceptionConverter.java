package mylearning.servicecomb2.springboot2.servlet.exception;

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
public class CustomizedExceptionConverter implements ExceptionToProducerResponseConverter<CustomizedException> {

  private static final Logger LOG = LoggerFactory.getLogger(CustomizedExceptionConverter.class);

  public Class<CustomizedException> getExceptionClass() {
    return CustomizedException.class;
  }

  public Response convert(SwaggerInvocation swaggerInvocation, CustomizedException customerException) {
    LOG.info("CustomizedExceptionConverter.convert is invoked.");
//    Throwable e = new InvocationException(Status.BAD_REQUEST, customerException);
//    return Response.consumerFailResp(e);
    int statusCode = customerException.getErrorCode();
    String reasonPhrase = customerException.getErrorData();
    String errorData = customerException.getMessage();
    InvocationException state = new InvocationException(statusCode, reasonPhrase, errorData, customerException);
    return Response.failResp(state);
  }
}
