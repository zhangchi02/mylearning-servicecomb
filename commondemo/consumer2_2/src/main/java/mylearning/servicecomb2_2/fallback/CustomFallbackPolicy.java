package mylearning.servicecomb2_2.fallback;

import org.apache.servicecomb.bizkeeper.FallbackPolicy;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.stereotype.Component;

@Component
public class CustomFallbackPolicy implements FallbackPolicy {
  private static final String POLICY_NAME = "custom";

  @Override
  public String name() {
    return POLICY_NAME;
  }

  @Override
  public Response getFallbackResponse(Invocation invocation, Throwable error) {
    CommonExceptionData errorData = new CommonExceptionData("consumer CustomFallbackPolicy errorData");
    InvocationException exception = ExceptionFactory.createConsumerException(errorData);
    return Response.createFail(invocation.getInvocationType(), exception);
//    return Response.failResp(exception);
//    return Response.failResp(invocation.getInvocationType(), exception);
//    return Response.failResp(invocation.getInvocationType(),
//        BizkeeperExceptionUtils
//            .createBizkeeperException(BizkeeperExceptionUtils.SERVICECOMB_BIZKEEPER_FALLBACK,
//                exception,
//                invocation.getOperationMeta().getMicroserviceQualifiedName()));
  }
}
