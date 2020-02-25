package com.zhc.service;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestSchema(schemaId = "swagger")
@RequestMapping(path = "/provider/v0")
public class SwaggerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerService.class);

  @PutMapping("/update/readinfo")
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
    LOGGER.info("updateReadInfo() is invoked, terminal: {}, id: {}, operator: {}, name: {}.", terminal, id, operator,
        name);
    ResponseEntity<String> response = new ResponseEntity<String>("success", HttpStatus.OK);
    return response;
  }
}
