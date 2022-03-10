package mylearning.servicecomb2_2.handler;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler implements Handler {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

  public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
    LOGGER.info("ClientHandler handle.");
    invocation.next(asyncResp);
  }
}
