package mylearning.servicecomb2_1.filter;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.Cookie;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class CatchSpecialStatusFilter implements HttpServerFilter {

  private static final Logger LOG = LoggerFactory.getLogger(CatchSpecialStatusFilter.class);

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    LOG.info("CatchSpecialStatusFilter#afterReceiveRequest is invoked.");
    return null;
  }

  public CompletableFuture<Void> beforeSendResponseAsync(Invocation invocation, HttpServletResponseEx responseEx) {
    LOG.info("CatchSpecialStatusFilter#beforeSendResponse is invoked.");

    int status = responseEx.getStatusType().getStatusCode();
    String message = responseEx.getStatusType().getReasonPhrase();

    LOG.info("get status from responseEx. status:{}", status);
    LOG.info("get message from responseEx. message:{}", message);

    if (status == 429) {
      String myMessage = "my defined message:rejected by qps flowcontrol";
      responseEx.getBodyBuffer().setBytes(0, myMessage.getBytes());
    }
//    responseEx.setStatus(Status.BAD_REQUEST.getStatusCode(), "user");

    CompletableFuture<Void> future = new CompletableFuture<>();
    try {
      beforeSendResponse(invocation, responseEx);
      future.complete(null);
    } catch (Throwable e) {
      future.completeExceptionally(e);
    }
    return future;
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
