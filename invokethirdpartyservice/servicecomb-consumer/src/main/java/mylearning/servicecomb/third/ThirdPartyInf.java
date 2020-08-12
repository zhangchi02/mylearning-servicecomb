package mylearning.servicecomb.third;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import mylearning.springcloud.springboot2.pojo.Person;

@Path("/common")
@Api(produces = MediaType.TEXT_PLAIN)
public interface ThirdPartyInf {
  @Path("/sayHello")
  @GET
  String sayHello(@QueryParam("name") String name);

  @Path("/person")
  @POST
  Person person2(@HeaderParam("myHeader") String myHeader, Person person);
}
