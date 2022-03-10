package mylearning.servicecomb2_2.service;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

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
  public Student findStudent(@RequestParam(value = "aParty") String aParty) {
    Student s = new Student();
    s.setAParty(aParty);
    s.setBParty("test");
    LOGGER.info("invoke getstudent url.");
    return s;
  }

  @GetMapping(path = "/getStuCars")
  public mylearning.servicecomb.common2.Student findStuCars() {
    mylearning.servicecomb.common2.Student s = new mylearning.servicecomb.common2.Student();
    s.setCarsList(new ArrayList<>());
    LOGGER.info("invoke getStuCars url.");
    return s;
  }

  @PostMapping(path = "/savestudent")
  public Student saveStudent(@RequestBody Student student) {
    return student;
  }


  @PostMapping(path = "/mixInTest")
  public PageInfo mixInTest(@RequestBody PageInfo pageInfo) {
    return pageInfo;
  }

  @GetMapping("paramValidate")
  public String paramValidate(
      @Valid @Pattern(regexp = "^[a-fA-F0-9]{24}$") @RequestParam(value = "marker", defaultValue = "ffffffffffffffffffffffff", required = false) String marker,
      @RequestParam("offset") String offset) {
    LOGGER.info("marker:{},offset:{}.", marker, offset);
    return "success";
  }
}
