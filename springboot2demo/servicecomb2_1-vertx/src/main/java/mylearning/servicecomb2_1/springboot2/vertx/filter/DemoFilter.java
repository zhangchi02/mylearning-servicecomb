package mylearning.servicecomb2_1.springboot2.vertx.filter;

import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoFilter implements HttpServerFilter {

  private static final Logger LOG = LoggerFactory.getLogger(DemoFilter.class);

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    LOG.info("provider afterReceiveRequest is invoked, uri:{}, parameters:{}.",
        invocation.getRequestEx().getRequestURI(), invocation.getRequestEx().getParameterMap());
    return null;
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
