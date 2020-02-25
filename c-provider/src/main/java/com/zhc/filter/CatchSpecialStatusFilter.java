package com.zhc.filter;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.Cookie;

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
    LOG.info("get status from responseEx. status:{}", responseEx.getStatusType().getStatusCode());
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
