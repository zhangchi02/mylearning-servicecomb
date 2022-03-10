package mylearning.servicecomb2.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;

import mylearning.servicecomb.common.Person;
import mylearning.servicecomb.common.Teacher;
import mylearning.servicecomb.service.ICommonService;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "commonService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class CommonService implements ICommonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  private DynamicLongProperty helloDelay = DynamicPropertyFactory.getInstance().getLongProperty("delay.sayHello", 0);

  @RequestMapping(path = "/hello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(value = "name") String name) {
    if ("delay".equalsIgnoreCase(name)) {
      try {
        Thread.sleep(helloDelay.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return "Hello," + name;
  }

  @RequestMapping(path = "/bak/hello", method = RequestMethod.GET)
  public String sayHelloBak(@RequestParam(value = "name") String name) {
    return "bak,Hello," + name;
  }

  @GetMapping("/hello/{pathVariable}/haha")
  public String testPathVariable(@PathVariable(name = "pathVariable") String pathVariable) {
    LOGGER.info("CommonService.testPathVariable is invoked. PathVariable: {}.", pathVariable);
    return pathVariable;
  }

  @Override
  public String listPerson(List<Person> persons) {
    LOGGER.info("persons.size: {}", persons.size());
    return "success";
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

  @PostMapping("/listTeacher")
//Caused by: java.lang.IllegalStateException: parameter name is not present, method=mylearning.servicecomb2.service.CommonService:listTeacher
//  solution:
//  change pom.xml, add compiler argument: -parameters, for example:
//    <plugin>
//      <groupId>org.apache.maven.plugins</groupId>
//      <artifactId>maven-compiler-plugin</artifactId>
//      <configuration>
//        <compilerArgument>-parameters</compilerArgument>
//      </configuration>
//    </plugin>
//  public List<Teacher> listTeacher(@RequestParam String name) {
  public List<Teacher> listTeacher(@RequestParam("name") String name) {
    Teacher teacher = new Teacher();
    teacher.setName(name);
    List<Teacher> listTeacher = new ArrayList<>();
    listTeacher.add(teacher);
    LOGGER.info("listTeacher() is invoked.");
    return listTeacher;
  }

  @PostMapping("/queryTeacher")
  public Teacher queryTeacher(@Valid @RequestBody Teacher teacher) {
    LOGGER.info("queryTeacher() is invoked.");
    return teacher;
  }

  @RequestMapping(path = "/hihihi", method = RequestMethod.GET)
  public String hihihi(@RequestParam(value = "name") String name) {
    return "Hi," + name;
  }


  @RequestMapping(path = "/callAsProvider", method = RequestMethod.GET)
  public String callAsProvider(@RequestParam(value = "name") String name) {
    String url = String.format("cse://consumer/consumer/v0/asProvider?name=%s", name);
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    return response.getBody();
  }

  @PostMapping(path = "/formUrlEncode")
  public String formUrlEncode(@RequestBody Teacher teacher) {
    LOGGER.info("formUrlEncode() is invoked. teacher: {}.", teacher);
    return "success";
  }

  @RequestMapping(path = "/haha", method = RequestMethod.GET)
  public String haha(@RequestParam(value = "name") String name) {
    return "haha," + name;
  }
}
