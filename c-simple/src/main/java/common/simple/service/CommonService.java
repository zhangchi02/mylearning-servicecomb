package common.simple.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import mylearning.servicecomb.common.Animal;
import mylearning.servicecomb.common.DeductFreeResourceRsp;
import mylearning.servicecomb.common.Dog;

@RestSchema(schemaId = "commonService")
@RequestMapping(path = "/simple/v0/")
public class CommonService {
  private static final Logger LOG = LoggerFactory.getLogger(CommonService.class);

  private DynamicStringProperty test =
      DynamicPropertyFactory.getInstance().getStringProperty("test", "", notifyConfigRefreshed());

  private Runnable notifyConfigRefreshed() {
    return () -> {
      LOG.info("config[test] changed to [{}]!", test.getValue());
    };
  }

  @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(name = "name") String name) {
    LOG.info("sayHello is invoked.");
    return "Hello, " + name;
  }

  @RequestMapping(value = "/sayNo", method = RequestMethod.GET)
  public String sayNo(@RequestParam(name = "name") String name) {
    LOG.info("sayNo is invoked.");
    return "No, " + name;
  }

  @RequestMapping(value = "/sayYes", method = RequestMethod.GET)
  public String sayYes(@RequestParam(name = "name") String name) {
    LOG.info("sayYes is invoked.");
    return "Yes, " + name;
  }

  @RequestMapping(value = "/deductFreeResources", method = RequestMethod.GET)
  public DeductFreeResourceRsp deductFreeResources(@RequestParam(name = "freeResUsages") String freeResUsages) {
    LOG.info("Start to handle deductFreeResources.req is {}.", freeResUsages);
    ObjectMapper mapper = new ObjectMapper();
    DeductFreeResourceRsp deductFreeResourceRsp = null;
    try {
      deductFreeResourceRsp = mapper.readValue(freeResUsages, DeductFreeResourceRsp.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    LOG.info("deductFreeResourceRsp:{}.", deductFreeResourceRsp);
    return deductFreeResourceRsp;
  }

  @RequestMapping(value = "/findAnimals", method = RequestMethod.POST)
  public Animal findAnimals(@RequestBody Dog dog) {
    LOG.info("findAnimals dog: {}.", dog);
//    findAnimals dog: Dog{sort=5992104113028738701, weight=5992104113028739072, age=599210411, price=5992104113028738701.0}.
//    findAnimals dog: Dog{sort=5992104113028738701, weight=5992104113028738701.0, age=599210411, price=5992104113028738701.0}.
    List<Dog> dogList = new ArrayList<>();
    dogList.add(dog);
    Animal animal = new Animal(new BigDecimal(1), dogList);
    LOG.info("animal: {}.", animal);
    return animal;
  }

  @RequestMapping(value = "/findDogs", method = RequestMethod.GET)
  public Dog findDogs(@RequestParam(name = "sort") BigDecimal sort,
      @RequestParam(name = "weight") BigDecimal weight, @RequestParam(name = "age") BigDecimal age,
      @RequestParam(name = "price") BigDecimal price) {
    LOG.info("sort: {},weight: {},age: {},price: {},.", sort, weight, age, price);
    Dog dog = new Dog(sort, weight, age, price);
    LOG.info("findDogs dog: {}.", dog);
    return dog;
  }
}
