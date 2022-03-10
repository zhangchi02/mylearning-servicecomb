package mylearning.servicecomb2.third;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import mylearning.springcloud.springboot2webappjar.pojo.Person;

@Path("/common")
@Api(produces = MediaType.TEXT_PLAIN)
public interface ThirdPartyInf {
  @Path("/sayHello")
  @GET
  String sayHello(@QueryParam("name") String name);

  @Path("/person")
  @POST
//  @ApiImplicitParams({
//      @ApiImplicitParam(name = "myHeader", value = "姓名", dataType = "string", paramType = "header"),
//      @ApiImplicitParam(name = "person", value = "null", dataType = "object", paramType = "body")
//  })
//   java.lang.ClassCastException: java.lang.Class cannot be cast to java.lang.reflect.ParameterizedType
//  ResponseEntity person2(@HeaderParam("myHeader") String myHeader, Person person);
  Person person2(@HeaderParam("myHeader") String myHeader, Person person);
}
