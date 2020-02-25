package com.zhc.filter;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.common.rest.RestConst;
import org.apache.servicecomb.common.rest.codec.RestCodec;
import org.apache.servicecomb.common.rest.definition.RestOperationMeta;
import org.apache.servicecomb.common.rest.filter.inner.ServerRestArgsFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.core.definition.OperationMeta;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.DynamicPropertyFactory;

//public class CatchExceptionFilter{
public class CatchArgsExceptionFilter extends ServerRestArgsFilter {
  private static final Logger LOG = LoggerFactory.getLogger(CatchArgsExceptionFilter.class);

  @Override
  public boolean enabled() {
    return true;
  }

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx requestEx) {
    try {
      LOG.info("CatchArgsExceptionFilter.afterReceiveRequest is invoked.");
      super.afterReceiveRequest(invocation, requestEx);
    } catch (Exception e) {
      LOG.error("Exception: {}.", e);
      e.printStackTrace();
      CustomErrorRsponse response = new CustomErrorRsponse();
      response.setMsg("xxx" + e.getMessage());
      return Response.create(Status.BAD_REQUEST, response);
    }
    return null;
  }

  public static class CustomErrorRsponse {
    private String msg;

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public String getMsg() {
      return msg;
    }
  }
}
