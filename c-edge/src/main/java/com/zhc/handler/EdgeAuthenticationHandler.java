package com.zhc.handler;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhc.filter.EdgeAuthenticationFilter;

/**
* @author zhangchi02
* @date 2019年3月19日
*/
public class EdgeAuthenticationHandler implements Handler {

  private static final Logger LOGGER = LoggerFactory.getLogger(EdgeAuthenticationHandler.class);

  @Override
  public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
    String username = invocation.getContext(EdgeAuthenticationFilter.USERNAME);
    String password = invocation.getContext(EdgeAuthenticationFilter.PASSWORD);
    LOGGER.info("username:{},password:{}", username, password);
    if (null == username || username.equals(password)) {
      asyncResp.consumerFail(new InvocationException(Status.UNAUTHORIZED, "Wrong authentication information."));
      return;
    }
    invocation.next(asyncResp);
  }

}
