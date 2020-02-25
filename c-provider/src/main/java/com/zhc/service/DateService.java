package com.zhc.service;

import java.util.Date;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhc.common.DateBO;
import com.zhc.common.DateVO;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "providerSay") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class DateService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DateService.class);

  @PostMapping("/date")
  @ResponseBody
  public DateVO date(@RequestBody DateVO dateVO) {
    LOGGER.info("date() is invoked, date: {}.", dateVO);
    return dateVO;
  }

  @PostMapping("/date/append")
  @ResponseBody
  public DateVO dateAppend(@RequestBody DateVO dateVO) {
    LOGGER.info("dateAppend() is invoked, date: {}.", dateVO);
    return dateVO;
  }

  @PostMapping("/dateBO")
  public DateBO dateBO(@RequestBody DateBO dateBO) {
    return dateBO;
  }

  @GetMapping("/date2")
  public Date date2(Date date) {
    LOGGER.info("date2() is invoked, date: {}.", date);
    return date;
  }
}
