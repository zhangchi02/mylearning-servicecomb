package mylearning.servicecomb.service;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.servicecomb.foundation.vertx.http.VertxServerRequestToHttpServletRequest;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@RestSchema(schemaId = "cookieServiceConsumer")
@Path("/consumer/v0")
public class CookieServiceConsuemr {
  private static final Logger LOGGER = LoggerFactory.getLogger(CookieServiceConsuemr.class);

  @GET
  @Path("/getCookie")
  public String getCookie(){
//    VertxServerRequestToHttpServletRequest vs = BeanUtils.instantiate(VertxServerRequestToHttpServletRequest.class);
    return "";
  }

}
