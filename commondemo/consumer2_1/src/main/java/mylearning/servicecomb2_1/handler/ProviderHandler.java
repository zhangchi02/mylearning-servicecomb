package mylearning.servicecomb2_1.handler;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProviderHandler implements Handler {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProviderHandler.class);

  public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
    LOGGER.info("ProviderHandler handle.");
    invocation.next(asyncResp);
  }
}
