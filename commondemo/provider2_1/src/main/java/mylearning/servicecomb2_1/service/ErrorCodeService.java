package mylearning.servicecomb2_1.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.extend.annotations.ResponseHeaders;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mylearning.servicecomb.common.Gender;
import mylearning.servicecomb.common.GreetingResponse;
import mylearning.servicecomb.common.Person;
import mylearning.servicecomb2_1.exception.CustomizedException;
import mylearning.servicecomb2_1.simple.MultiRequest;
import mylearning.servicecomb2_1.simple.MultiResponse200;
import mylearning.servicecomb2_1.simple.MultiResponse400;
import mylearning.servicecomb2_1.simple.MultiResponse404;
import mylearning.servicecomb2_1.simple.MultiResponse500;
import mylearning.servicecomb2_1.simple.NoClientErrorCode200;
import mylearning.servicecomb2_1.simple.NoClientErrorCode400;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "errorCodeService")
@Path("/provider/v0")
public class ErrorCodeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodeService.class);

  @Path("/errorCode")
  @POST
  @ApiResponses({
      @ApiResponse(code = 200, response = MultiResponse200.class, message = ""),
      @ApiResponse(code = 400, response = MultiResponse400.class, message = ""),
      @ApiResponse(code = 404, response = MultiResponse404.class, message = ""),
      @ApiResponse(code = 500, response = MultiResponse500.class, message = "")})
  public MultiResponse200 errorCode(MultiRequest request) {
    if (request.getCode() == 400) {
      MultiResponse400 r = new MultiResponse400();
      r.setCode(400);
      r.setMessage("bad request.");
      //直接抛出InvocationException异常，ServiceComb可以直接识别
      throw new InvocationException(Status.BAD_REQUEST, r);
    } else if (request.getCode() == 500) {
      MultiResponse500 r = new MultiResponse500();
      r.setCode(500);
      r.setMessage("internal error.");
      throw new InvocationException(Status.INTERNAL_SERVER_ERROR, r);
    } else if (request.getCode() == 404) {
      MultiResponse404 r = new MultiResponse404();
      r.setCode(404);
      r.setMessage("not found.");
      throw new InvocationException(Status.NOT_FOUND, r);
    } else {
      MultiResponse200 r = new MultiResponse200();
      r.setCode(200);
      r.setMessage("success result.");
      return r;
    }
  }

  @Path("/errorCodeWithHeader")
  @POST
  @ApiResponses({
      @ApiResponse(code = 200, response = MultiResponse200.class, message = ""),
      @ApiResponse(code = 400, response = MultiResponse400.class, message = ""),
      @ApiResponse(code = 404, response = MultiResponse404.class, message = ""),
      @ApiResponse(code = 500, response = MultiResponse500.class, message = "")})
  @ResponseHeaders({@ResponseHeader(name = "x-code", response = String.class)})
  public Response errorCodeWithHeader(MultiRequest request) {
    Response response = new Response();
//    if (request.getCode() == 400) {
//      MultiResponse400 r = new MultiResponse400();
//      r.setCode(400);
//      r.setMessage("bad request");
//      response.setStatus(Status.BAD_REQUEST);
//      // If got many types for different status code, we can only using InvocationException for failed error code like 400-500.
//      // The result for Failed Family(e.g. 400-500), can not set return value as target type directly or will give exception.
//      response.setResult(new InvocationException(Status.BAD_REQUEST, r));
//      response.getHeaders().addHeader("x-code", "400");
//    } else if (request.getCode() == 404) {
//      MultiResponse404 r = new MultiResponse404();
//      r.setCode(404);
//      r.setMessage("not found");
//      response.setStatus(Status.NOT_FOUND);
//      response.setResult(new InvocationException(Status.NOT_FOUND, r));
//      response.getHeaders().addHeader("x-code", "404");
//    } else if (request.getCode() == 500) {
//      MultiResponse500 r = new MultiResponse500();
//      r.setCode(500);
//      r.setMessage("internal error");
//      response.setStatus(Status.INTERNAL_SERVER_ERROR);
//      response.setResult(new InvocationException(Status.INTERNAL_SERVER_ERROR, r));
//      response.getHeaders().addHeader("x-code", "500");
//    } else {
//      MultiResponse200 r = new MultiResponse200();
//      r.setCode(200);
//      r.setMessage("success result");
//      response.setStatus(Status.OK);
//      // If error code is OK family(like 200), we can use the target type.
//      response.setResult(r);
//      response.getHeaders().addHeader("x-code", "200");
//    }
    return response;
  }

  @Path("/errorCodeWithHeaderJAXRS")
  @POST
  @ApiResponses({
      @ApiResponse(code = 200, response = MultiResponse200.class, message = ""),
      @ApiResponse(code = 400, response = MultiResponse400.class, message = ""),
      @ApiResponse(code = 404, response = MultiResponse404.class, message = ""),
      @ApiResponse(code = 500, response = MultiResponse500.class, message = "")})
  @ResponseHeaders({@ResponseHeader(name = "x-code", response = String.class)})
  public javax.ws.rs.core.Response errorCodeWithHeaderJAXRS(MultiRequest request) {
    javax.ws.rs.core.Response response = null;
//    if (request.getCode() == 400) {
//      MultiResponse400 r = new MultiResponse400();
//      r.setCode(request.getCode());
//      r.setMessage(request.getMessage());
//      // If got many types for different status code, we can only using InvocationException for failed error code like 400-500.
//      // The result for Failed Family(e.g. 400-500), can not set return value as target type directly or will give exception.
//      response = javax.ws.rs.core.Response.status(Status.BAD_REQUEST)
//          .entity(new InvocationException(Status.BAD_REQUEST, r))
//          .header("x-code", "400")
//          .build();
//    } else if (request.getCode() == 404) {
//      MultiResponse404 r = new MultiResponse404();
//      r.setCode(request.getCode());
//      r.setMessage(request.getMessage());
//      response = javax.ws.rs.core.Response.status(Status.NOT_FOUND)
//          .entity(new InvocationException(Status.NOT_FOUND, r))
//          .header("x-code", "404")
//          .build();
//    } else if (request.getCode() == 500) {
//      MultiResponse500 r = new MultiResponse500();
//      r.setCode(request.getCode());
//      r.setMessage(request.getMessage());
//      response = javax.ws.rs.core.Response.status(Status.INTERNAL_SERVER_ERROR)
//          .entity(new InvocationException(Status.INTERNAL_SERVER_ERROR, r))
//          .header("x-code", "500")
//          .build();
//    } else {
//      MultiResponse200 r = new MultiResponse200();
//      r.setCode(request.getCode());
//      r.setMessage(request.getMessage());
//      // If error code is OK family(like 200), we can use the target type.
//      response = javax.ws.rs.core.Response.status(Status.OK)
//          .entity(r)
//          .header("x-code", "200")
//          .build();
//    }
    return response;
  }

  @Path("/noClientErrorCode")
  @POST
  @ApiResponses({
      @ApiResponse(code = 400, response = NoClientErrorCode400.class, message = "")})
  public List<NoClientErrorCode200> noClientErrorCode(MultiRequest request) {
    if (request.getCode() == 400) {
      NoClientErrorCode400 r = new NoClientErrorCode400();
      r.setCode(request.getCode());
      r.setMessage(request.getMessage());
      r.setT400(400);
      throw new InvocationException(Status.BAD_REQUEST, r);
    } else {
      NoClientErrorCode200 r = new NoClientErrorCode200();
      r.setCode(request.getCode());
      r.setMessage(request.getMessage());
      r.setT200(200);
      List<NoClientErrorCode200> result = new ArrayList<>();
      result.add(r);
      return result;
    }
  }
}
