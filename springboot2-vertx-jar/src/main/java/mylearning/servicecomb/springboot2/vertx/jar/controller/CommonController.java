package mylearning.servicecomb.springboot2.vertx.jar.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestSchema(schemaId = "commonService")
@RequestMapping(path = "/springboot2vertxjar/v0")
public class CommonController {
  private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name;
  }

  @PutMapping("update/readinfo")
  @ApiOperation("更新阅读信息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "terminal", value = "终端系统类型", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "id", value = "消息接收记录id", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "operator", value = "阅读人Id", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "name", value = "阅读人姓名", dataType = "string", paramType = "query")
  })
  @ResponseBody
  public ResponseEntity<String> updateReadInfo(String terminal, String id,
      String operator, String name) {
    LOG.info("updateReadInfo() is invoked, terminal:{}, id:{}, operator:{}, name:{}.", terminal, id, operator, name);
    ResponseEntity<String> responseEntity = new ResponseEntity<>("updateReadInfo success", HttpStatus.OK);
    return responseEntity;
  }

  @PutMapping("revocation")
  @ApiOperation("撤销消息（站内信可撤销）")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "主键", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "operator", value = "撤销人id", dataType = "string", paramType = "query"),
      @ApiImplicitParam(name = "name", value = "操作人姓名", dataType = "string", paramType = "query")
  })
  @ResponseBody
  public ResponseEntity<String> updateRevocation(String id, String operator, String name) {
    LOG.info("updateRevocation() is invoked, id:{}, operator:{}, name:{}.", id, operator, name);
    ResponseEntity<String> responseEntity = new ResponseEntity<>("updateRevocation success", HttpStatus.OK);
    return responseEntity;
  }

  @DeleteMapping("delete")
  @ApiOperation("根据模板id删除模板信息")
  @ApiImplicitParam(name = "ids", value = "要删除数据id字符串,格式：'1','2','3'", dataType = "string", paramType = "query")
  @ResponseBody
  public ResponseEntity<String> deleteTemplate(String ids) {
    LOG.info("deleteTemplate() is invoked, ids:{}.", ids);
    ResponseEntity<String> responseEntity = new ResponseEntity<>("deleteTemplate success", HttpStatus.OK);
    return responseEntity;
  }

  @RequestMapping(path = "/sayHi", method = RequestMethod.GET)
  public String sayHi(@RequestParam(name = "name") String name) {
    return "Hi, " + name;
  }
}
