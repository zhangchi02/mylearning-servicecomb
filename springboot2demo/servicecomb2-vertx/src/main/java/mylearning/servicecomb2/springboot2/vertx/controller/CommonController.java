package mylearning.servicecomb2.springboot2.vertx.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestSchema(schemaId = "CommonController")
@RequestMapping(path = "/servicecomb2vertx/v0")
public class CommonController {
  private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

  private DynamicStringProperty sayHiPrefix =
      DynamicPropertyFactory.getInstance().getStringProperty("hi.sayHiPrefix", "", notifyConfigRefreshed());

  private Runnable notifyConfigRefreshed() {
    return () -> {
      LOG.info("config[hi.sayHiPrefix] changed to {}!", sayHiPrefix.getValue());
    };
  }

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam(name = "name") String name) {
    return sayHiPrefix.getValue() + name;
  }

  @GetMapping("paramValidate")
  public String paramValidate(
      @Valid @Pattern(regexp = "^[a-fA-F0-9]{24}$") @RequestParam(value = "marker", defaultValue = "ffffffffffffffffffffffff", required = false) String marker,
      @RequestParam("offset") String offset) {
    LOG.info("marker:{},offset:{}.", marker, offset);
    return "success";
  }

}
