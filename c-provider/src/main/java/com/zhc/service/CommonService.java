package com.zhc.service;

import java.io.FileNotFoundException;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.zhc.common.Teacher;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "commonService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class CommonService implements ICommonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

  private DynamicLongProperty helloDelay = DynamicPropertyFactory.getInstance().getLongProperty("delay.sayHello", 0);

  @RequestMapping(path = "/hello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(value = "name") String name) {
    if("delay".equalsIgnoreCase(name)){
      try {
        Thread.sleep(helloDelay.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return "Hello," + name;
  }

  @GetMapping("/hello/{pathVariable}/haha")
  public String testPathVariable(@PathVariable(name = "pathVariable") String pathVariable) {
    LOGGER.info("CommonService.testPathVariable is invoked. PathVariable: {}.", pathVariable);
    return pathVariable;
  }

  @GetMapping("/shutdown")
  public String shutdown(@RequestParam(name = "method") String method) {
    try {
      LOGGER.info("begin sleep time: {}.", System.currentTimeMillis());
      Thread.sleep(helloDelay.get());
      LOGGER.info("  end sleep time: {}.", System.currentTimeMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (method.equalsIgnoreCase("shutdown")) {
      System.exit(0);
    }
    return method;
  }

  @PostMapping("/queryTeacher")
  public Teacher queryTeacher(@RequestBody Teacher teacher) {
    LOGGER.info("queryTeacher() is invoked.");
    return teacher;
  }

  @RequestMapping(path = "/hihihi", method = RequestMethod.GET)
  public String hihihi(@RequestParam(value = "name") String name) {
    return "Hi," + name;
  }
}
