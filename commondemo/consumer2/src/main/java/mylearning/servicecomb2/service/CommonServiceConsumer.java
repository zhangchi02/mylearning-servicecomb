package mylearning.servicecomb2.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;

import mylearning.servicecomb.service.CommonServiceReactive;
import mylearning.servicecomb.service.ICommonService;
import mylearning.servicecomb2.common.Animal;
import mylearning.servicecomb2.common.DeductFreeResourceRsp;
import mylearning.servicecomb2.common.Dog;

import mylearning.servicecomb.common.DateVO;
import mylearning.servicecomb.common.Person;
import mylearning.servicecomb.common.Teacher;

/**
 * @author zhangchi02
 * @date 2019年3月4日
 */
@RestSchema(schemaId = "commonServiceConsumer")
@Path("/consumer/v0") //这里使用JAX-PS风格开发consumer服务
public class CommonServiceConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceConsumer.class);

  //RPC调用方式需要声明一个provider服务的REST借口代理
  @RpcReference(microserviceName = "provider", schemaId = "commonService")
//  @RpcReference(microserviceName = "provider")
//  Schema not exist, microserviceName=provider, schemaId=, consumer interface=mylearning.servicecomb2.service.ICommonService; new producer not running or not deployed.
  private ICommonService commonService;

  @RpcReference(microserviceName = "provider", schemaId = "commonService")
  private CommonServiceReactive commonServiceReactive;

  //RestTemplate调用方式需要创建一个ServiceComb的RestTemplate
  private RestTemplate restTemplate = RestTemplateBuilder.create();

  private DynamicLongProperty helloDelay = DynamicPropertyFactory.getInstance().getLongProperty("delay.hello", 0);

  @Path("/hello")
  @GET
  public String hello(@QueryParam("name") String name) {
    if (helloDelay.get() > 0) {
      try {
        LOGGER.info("begin sleep: {}.", System.currentTimeMillis());
        Thread.currentThread().sleep(helloDelay.get());
        LOGGER.info("  end sleep: {}.", System.currentTimeMillis());
      } catch (InterruptedException e) {
        LOGGER.error("sayHello sleeping is interrupted!", e);
      }
    }
    //RPC调用方式体验与本地调用相同
    String response = commonService.sayHello(name);
    LOGGER.info("get response: {}", response);
    return response;
  }

  @Path("/helloReactive")
  @GET
  public CompletableFuture<String> helloReactive(@QueryParam("name") String name) {
    if (helloDelay.get() > 0) {
      try {
        LOGGER.info("begin sleep.");
        Thread.sleep(helloDelay.get());
        LOGGER.info("  end sleep.");
      } catch (InterruptedException e) {
        LOGGER.error("helloReactive sleeping is interrupted!", e);
      }
    }
    CompletableFuture<String> response = new CompletableFuture<String>();
    commonServiceReactive.sayHello(name).whenComplete((result, throwable) -> {
      if (null != throwable) {
        LOGGER.error("async invoke sayHello failed!", throwable);
        response.completeExceptionally(throwable);
        return;
      }
      LOGGER.info("async invoke sayHello success. response: {}", result);
      response.complete(result);
    });
    return response;
  }

  @Path("/test")
  @GET
  public String test(@QueryParam("test") String test) throws Exception {
    ResponseEntity<String> response = null;
    if ("hihihi".equalsIgnoreCase(test)) {
      response = restTemplate.getForEntity("cse://provider/provider/v0/hihihi?name=hihihi", String.class);
      return response.getBody();
    }
    if ("path".equalsIgnoreCase(test)) {
      response = restTemplate.getForEntity("cse://provider/provider/v0?content=hello", String.class);
      return response.getBody();
    }
    return commonService.testPathVariable(test);
  }

  @Path("/sleep")
  @GET
  public String sleep(@QueryParam("test") String test) {
    LOGGER.info("invoke sleep url.");
    try {
      Thread.sleep(40000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return test;
  }

  @POST
  @Path("/resttem")
  public DateVO date(@QueryParam("test") String test) {
    DateVO dv = new DateVO();
    dv.setMonth(new Date());

    DateVO result = null;
    ResponseEntity<DateVO> response = null;
    if (test.equalsIgnoreCase("object")) {
      LOGGER.info("postForObject");
      result = restTemplate.postForObject("cse://provider/provider/v0/date", dv, DateVO.class);
      return result;
    } else {
      LOGGER.info("postForEntity");
      response = restTemplate.postForEntity("cse://provider/provider/v0/date", dv, DateVO.class);
      return response.getBody();
    }
  }

  @Path("/queryTeacher")
  @GET
  public String queryTeacher(Teacher teacher) {
    System.out.println("queryTeacher() is called, teacher = [" + teacher + "]");
    if (teacher.getName().equalsIgnoreCase("restTemplate")) {
      Map<String, Object> body = new HashMap<String, Object>();
      body.put("name", "zhangsan");
      body.put("age", 28);
      String url = "cse://provider/provider/v0/queryTeacher";
      Teacher teac = restTemplate.postForObject(url, body, Teacher.class);
      LOGGER.info("teacher:{}.", teac);
    }
    return "Hello, your name is " + teacher.getName() + ", and age is " + teacher.getAge();
  }

  @Path("/callSimple")
  @GET
  public String callSimple(@QueryParam("method") String method, @QueryParam("name") String name,
      @QueryParam("num") int num) {
    String url = "cse://simple/simple/v0/%s?name=%s";

    String queryParams = Strings.repeat(name, num);
    String longUri = String.format(url, "sayHi", queryParams);

    String sayHi = String.format(url, "sayHi", name);
    String sayHello = String.format(url, "sayHello", name);
    String sayYes = String.format(url, "sayYes", name);
    String sayNo = String.format(url, "sayNo", name);
    String sayOh = String.format(url, "sayOh", name);

    String result = null;

    if ("longUri".equalsIgnoreCase(method)) {
      result = getResult(longUri);
    }
    if ("sayHi".equalsIgnoreCase(method)) {
      result = getResult(sayHi);
    }
    if ("sayHello".equalsIgnoreCase(method)) {
      result = getResult(sayHello);
    }
    if ("sayYes".equalsIgnoreCase(method)) {
      result = getResult(sayYes);
    }
    if ("sayNo".equalsIgnoreCase(method)) {
      result = getResult(sayNo);
    }
    if ("sayOh".equalsIgnoreCase(method)) {
      result = getResult(sayOh);
    }
    if ("findAnimals".equalsIgnoreCase(method)) {
      restTemplate.getForEntity("cse://simple/simple/v0/findAnimals?sort", String.class).getBody();
    } else {
      result = getResult(String.format(url, method, name));
    }

    return result;
  }

  private String getResult(String url) {
    return restTemplate.getForEntity(url, String.class).getBody();
  }

  @Path("/callProviderThrowException")
  @GET
  public String callProviderThrowException(@QueryParam("kind") String kind, @QueryParam("num") Integer num,
      @QueryParam("code") String code) {
    LOGGER.info("CommonServiceConsumer.callProviderThrowException is invoked.");
    String url = String.format("cse://provider/provider/v0/throwException?kind=%s&num=%s&code=%s", kind, num, code);
    LOGGER.info("url: {}.", url);
    String result = restTemplate.getForEntity(url, String.class).getBody();
    return result;
  }

  @Path("/findAnimals")
  @GET
  public Animal findAnimals(@QueryParam("sort") Long sort, @QueryParam("weight") Double weight,
      @QueryParam("age") Integer age, @QueryParam("price") String price) {
    LOGGER.info("findAnimals sort: {},weight: {},age: {},price: {}.", sort, weight, age, price);
//  sort: 5992104113028738701,weight: 5.9921041130287391E18,age: 599210411,price: 5992104113028738701.0,.
    String url = "cse://simple/simple/v0/%s";
    String findAnimalsUrl = String.format(url, "findAnimals");
    Dog dog = new Dog(new BigDecimal(sort), new BigDecimal(weight), new BigDecimal(age), new BigDecimal(price));
    LOGGER.info("dog: {}.", dog);
//  dog: Dog{sort=5992104113028738701, weight=5992104113028739072, age=599210411, price=5992104113028738701.0}.
    ResponseEntity<Animal> response = restTemplate.postForEntity(findAnimalsUrl, dog, Animal.class);
    return response.getBody();
  }

  @Path("/queryAnimal")
  @GET
  public Animal queryAnimal(@RequestBody Dog dog) {
    LOGGER.info("queryAnimal dog: {}.", dog);
//    queryAnimal dog: Dog{sort=5992104113028738701, weight=5992104113028738701.0, age=599210411, price=5992104113028738701.0}.
    String url = "cse://simple/simple/v0/%s";
    String findAnimalsUrl = String.format(url, "findAnimals");
    ResponseEntity<Animal> response = restTemplate.postForEntity(findAnimalsUrl, dog, Animal.class);
    return response.getBody();
  }


  @Path("/callDeductFreeResources")
  @GET
  public DeductFreeResourceRsp callDeductFreeResources(@QueryParam("freeResUsages") String freeResUsages) {
    LOGGER.info("freeResUsages: {}.", freeResUsages);
    Map<String, Object> map = new HashMap<>();
    map.put("key", freeResUsages);
    String url = "cse://simple/simple/v0/deductFreeResources?freeResUsages={key}";
    ResponseEntity<DeductFreeResourceRsp> response = restTemplate
        .getForEntity(url, DeductFreeResourceRsp.class, map);
    DeductFreeResourceRsp deductFreeResourceRsp = response.getBody();
    LOGGER.info("deductFreeResourceRsp:{}.", deductFreeResourceRsp);
    return deductFreeResourceRsp;
  }

  @Path("/findDogs")
  @GET
  public Dog findDogs(@QueryParam("sort") Long sort, @QueryParam("weight") Double weight,
      @QueryParam("age") Integer age, @QueryParam("price") String price) {
    LOGGER.info("findDog sort: {},weight: {},age: {},price: {}.", sort, weight, age, price);
//    findDog sort: 5992104113028738701,weight: 5.9921041130287391E18,age: 599210411,price: 5992104113028738701.0,.
    String url = "cse://simple/simple/v0/findDogs?sort=%s&weight=%s&age=%s&price=%s";
    String findAnimalsUrl = String.format(url, sort, weight, age, price);
    ResponseEntity<Dog> response = restTemplate.getForEntity(findAnimalsUrl, Dog.class);
    return response.getBody();
  }

  @Path("/queryDog")
  @GET
  public Dog queryDog(@QueryParam("sort") BigDecimal sort,
      @QueryParam("weight") BigDecimal weight, @QueryParam("age") BigDecimal age,
      @QueryParam("price") BigDecimal price) {
    LOGGER.info("queryDog sort: {},weight: {},age: {},price: {}.", sort, weight, age, price);
//    findDog sort: 5992104113028738701,weight: 5.9921041130287391E18,age: 599210411,price: 5992104113028738701.0,.
    String url = "cse://simple/simple/v0/findDogs?sort=%s&weight=%s&age=%s&price=%s";
    String findAnimalsUrl = String.format(url, sort, weight, age, price);
    ResponseEntity<Dog> response = restTemplate.getForEntity(findAnimalsUrl, Dog.class);
    return response.getBody();
  }

  @Path("/saveMyDTO")
  @GET
  public String saveMyDTO() {
//    findDog sort: 5992104113028738701,weight: 5.9921041130287391E18,age: 599210411,price: 5992104113028738701.0,.
    String url = "cse://contractfirst/contractfirst/v0/saveMyDTO";

    HttpHeaders headers = new HttpHeaders();
    headers.set("myHeader", "myHeaderValue");

    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("stringA", "中");
    paramMap.put("stringC", "StringCValue");
    paramMap.put("booleanA", true);

    HttpEntity<HashMap> entity = new HttpEntity<>(paramMap, headers);
    Object response = restTemplate.postForObject(url, entity, Object.class);
    String result = response.toString();
    LOGGER.info("response:{}.", result);
    return result;
  }

}
