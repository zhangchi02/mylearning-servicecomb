package mylearning.servicecomb.service;

import java.util.ArrayList;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import mylearning.servicecomb.common.Student;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "propertyService")
@RequestMapping(path = "/provider/v0")
public class PropertyService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

  @GetMapping(path = "/getstudent")
  public mylearning.servicecomb.common.Student findStudent(@RequestParam(value = "aParty") String aParty) {
    mylearning.servicecomb.common.Student s = new mylearning.servicecomb.common.Student();
    s.setAParty(aParty);
    s.setBParty("test");
    LOGGER.info("invoke getstudent url.");
    return s;
  }

  @GetMapping(path = "/getStuCars")
  public common.common2.common.Student findStuCars() {
    common.common2.common.Student s = new common.common2.common.Student();
    s.setCarsList(new ArrayList<>());
    LOGGER.info("invoke getStuCars url.");
    return s;
  }

  @PostMapping(path = "/savestudent")
  public Student saveStudent(@RequestBody mylearning.servicecomb.common.Student student) {
    return student;
  }


  @PostMapping(path = "/mixInTest")
  public PageInfo mixInTest(@RequestBody PageInfo pageInfo) {
    return pageInfo;
  }
}
