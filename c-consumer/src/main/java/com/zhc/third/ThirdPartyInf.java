package com.zhc.third;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;

@Path("/mylearning/servicecomb/common")
@Api(produces = MediaType.TEXT_PLAIN)
public interface ThirdPartyInf {
  @Path("/sayHello")
  @GET
  String sayHello(@QueryParam("name") String name);
}
