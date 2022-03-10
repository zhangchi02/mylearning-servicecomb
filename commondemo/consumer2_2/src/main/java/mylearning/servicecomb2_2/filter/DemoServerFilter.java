package mylearning.servicecomb2_2.filter;

import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServerFilter implements HttpServerFilter {
  private static final Logger LOG = LoggerFactory.getLogger(DemoServerFilter.class);

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public boolean enabled() {
    return true;
  }

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx requestEx) {
    LOG.info("DemoServerFilter afterReceiveRequest.");
    return null;
  }

  @Override
  public void beforeSendResponse(Invocation invocation, HttpServletResponseEx responseEx) {
    LOG.info("DemoServerFilter beforeSendResponse.");
  }
}
