package mylearning.servicecomb2_2.service;

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
import mylearning.servicecomb2_2.exception.CustomizedException;
import mylearning.servicecomb2_2.simple.MultiRequest;
import mylearning.servicecomb2_2.simple.MultiResponse200;
import mylearning.servicecomb2_2.simple.MultiResponse400;
import mylearning.servicecomb2_2.simple.MultiResponse404;
import mylearning.servicecomb2_2.simple.MultiResponse500;
import mylearning.servicecomb2_2.simple.NoClientErrorCode200;
import mylearning.servicecomb2_2.simple.NoClientErrorCode400;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "exceptionService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class ExceptionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionService.class);

  private GreetingResponse greetingResponse = new GreetingResponse();

  @GetMapping("/throwException")
  public String throwException(@RequestParam(name = "kind") String kind, @RequestParam(name = "num") Integer num,
      @RequestParam(name = "code") Integer code)
      throws Exception {
    LOGGER.info("ExceptionService.throwException is invoked.");
    if ("math".equalsIgnoreCase(kind)) {
      try {
        LOGGER.info("begin sleep time: {}.", System.currentTimeMillis());
        Thread.sleep(1500);
        LOGGER.info("  end sleep time: {}.", System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int i = 1/0;
    }
    if ("invocation".equalsIgnoreCase(kind)) {
      try {
        LOGGER.info("begin sleep time: {}.", System.currentTimeMillis());
        Thread.sleep(1500);
        LOGGER.info("  end sleep time: {}.", System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      throw new InvocationException(590, "exception code", "user defined error data");
    }
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
    return kind;
  }

  @PostMapping(path = "/greeting")
  public GreetingResponse greeting(@RequestBody Person person) {
    if (StringUtils.isEmpty(person.getName()) || null == person.getGender()) {
      throw new IllegalArgumentException("Lack of property");
    }
    if (Gender.MALE.equals(person.getGender())) {
      greetingResponse.setMsg("Hello, Mr." + person.getName());
    } else {
      greetingResponse.setMsg("Hello, Ms." + person.getName());
    }
    greetingResponse.setTimestamp(new Date());
    return greetingResponse;
  }

  //返回HttpStatus.NOT_FOUND会被拦截并返回590
  @PostMapping("/statusCode")
  public ResponseEntity<Person> errorCode(@RequestBody Person person) {
    ResponseEntity<Person> response = null;
    if (person.getName().equalsIgnoreCase("exception")) {
      response = new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
    } else {
      response = new ResponseEntity<Person>(person, HttpStatus.OK);
    }
    return response;
  }
}
