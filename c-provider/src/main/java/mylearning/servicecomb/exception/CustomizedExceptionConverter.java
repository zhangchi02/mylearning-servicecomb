package mylearning.servicecomb.exception;

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
public class CustomizedExceptionConverter implements ExceptionToProducerResponseConverter<CustomizedException> {

  private static final Logger LOG = LoggerFactory.getLogger(CustomizedExceptionConverter.class);

  public Class<CustomizedException> getExceptionClass() {
    return CustomizedException.class;
  }

  public Response convert(SwaggerInvocation swaggerInvocation, CustomizedException customerException) {
    LOG.info("CustomizedExceptionConverter.convert is invoked.");
    Throwable e = new InvocationException(Status.BAD_REQUEST, customerException);
    return Response.consumerFailResp(e);
  }
}
