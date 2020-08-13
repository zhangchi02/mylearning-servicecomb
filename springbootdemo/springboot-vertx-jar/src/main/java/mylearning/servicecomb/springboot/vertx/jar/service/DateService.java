package mylearning.servicecomb.springboot.vertx.jar.service;

import java.util.Date;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mylearning.servicecomb.springboot.vertx.jar.sample.DateVO;

@RestSchema(schemaId = "date")
@RequestMapping(path = "/")
public class DateService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DateService.class);

  @PostMapping("/date")
//  @ResponseBody
  public DateVO date(@RequestBody DateVO dateVO) {
    LOGGER.info("date() is invoked, dateVO is {}",dateVO);
    return dateVO;
  }

  @GetMapping("/datetime")
  public Date dateTime(Date date){
    LOGGER.info("dateTime() is invoked, date is {}",date);
    return date;
  }
}
