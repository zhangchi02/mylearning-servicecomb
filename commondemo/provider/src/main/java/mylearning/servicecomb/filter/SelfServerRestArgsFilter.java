package mylearning.servicecomb.filter;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.Part;

import org.apache.servicecomb.common.rest.RestConst;
import org.apache.servicecomb.common.rest.codec.RestCodec;
import org.apache.servicecomb.common.rest.codec.produce.ProduceProcessor;
import org.apache.servicecomb.common.rest.definition.RestOperationMeta;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.core.definition.OperationMeta;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.foundation.vertx.stream.BufferOutputStream;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

import com.netflix.config.DynamicPropertyFactory;

import io.netty.buffer.Unpooled;

public class SelfServerRestArgsFilter implements HttpServerFilter {
  @Override
  public int getOrder() {
    return -100;
  }

  @Override
  public boolean enabled() {
    return true;
  }

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx requestEx) {
    OperationMeta operationMeta = invocation.getOperationMeta();
    RestOperationMeta restOperationMeta = operationMeta.getExtData(RestConst.SWAGGER_REST_OPERATION);
    Object[] args = RestCodec.restToArgs(requestEx, restOperationMeta);
    invocation.setSwaggerArguments(args);
    return null;
  }

  @Override
  public CompletableFuture<Void> beforeSendResponseAsync(Invocation invocation, HttpServletResponseEx responseEx) {
    Response response = (Response) responseEx.getAttribute(RestConst.INVOCATION_HANDLER_RESPONSE);
    ProduceProcessor produceProcessor =
        (ProduceProcessor) responseEx.getAttribute(RestConst.INVOCATION_HANDLER_PROCESSOR);
    Object body = response.getResult();
    if(body instanceof InvocationException){
      body = ((InvocationException) body).getErrorData();
    }

    if (Part.class.isInstance(body)) {
      return responseEx.sendPart((Part) body);
    }

    responseEx.setContentType(produceProcessor.getName() + "; charset=utf-8");

    CompletableFuture<Void> future = new CompletableFuture<>();
    try (BufferOutputStream output = new BufferOutputStream(Unpooled.compositeBuffer())) {
      produceProcessor.encodeResponse(output, body);

      responseEx.setBodyBuffer(output.getBuffer());
      future.complete(null);
    } catch (Throwable e) {
      future.completeExceptionally(ExceptionFactory.convertProducerException(e));
    }
    return future;
  }
}
