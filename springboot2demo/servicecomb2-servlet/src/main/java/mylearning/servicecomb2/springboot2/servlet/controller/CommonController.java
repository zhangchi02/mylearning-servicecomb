package mylearning.servicecomb2.springboot2.servlet.controller;

import java.io.FileNotFoundException;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mylearning.servicecomb2.springboot2.servlet.exception.CustomizedException;

@RestSchema(schemaId = "CommonController")
@RequestMapping(path = "/servicecomb2servlet/v0")
public class CommonController {
  private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name + " , from commonService";
  }

  @GetMapping("/throwException")
  public String throwException(@RequestParam(name = "kind") String kind, @RequestParam(name = "num") Integer num,
      @RequestParam(name = "code") Integer code)
      throws Exception {
    LOG.info("ExceptionService.throwException is invoked.");
    //直接抛出异常, ServiceComb会拦截并返回590
    if ("known".equalsIgnoreCase(kind)) {
      throw new FileNotFoundException();
    }
    //若想实现类似@ControllerAdvice功能，需要借助异常扩展机制
    //实现ExceptionToProducerResponseConverter
    if ("custom".equalsIgnoreCase(kind) && code != 0) {
      throw new CustomizedException(code, "customized exception errorData.");
    }
    if ("proto".equalsIgnoreCase(kind) && code != 0) {
      throw new InvocationException(Status.BAD_REQUEST, "proto exception message.");
    }
    if ("null".equalsIgnoreCase(kind) && code != 0) {
      throw new NullPointerException();
    }
    return kind;
  }
}
