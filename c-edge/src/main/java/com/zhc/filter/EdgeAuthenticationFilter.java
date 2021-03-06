package com.zhc.filter;

import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class EdgeAuthenticationFilter implements HttpServerFilter {
	
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";

	@Override
	public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
		String username = httpServletRequestEx.getHeader(USERNAME);
		String password = httpServletRequestEx.getHeader(PASSWORD);
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return Response.consumerFailResp(new InvocationException(Status.UNAUTHORIZED,
					new CommonExceptionData("Lack of authentication information.")));
		}
		invocation.addContext(USERNAME, username);
		invocation.addContext(PASSWORD, password);
		return null;
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
