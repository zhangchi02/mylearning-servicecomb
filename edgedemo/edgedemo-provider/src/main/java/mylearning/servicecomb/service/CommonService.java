package mylearning.servicecomb.service;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mylearning.servicecomb.simple.TaskData;

@RestSchema(schemaId = "commonService")
@RequestMapping(path = "/edgedemoprovider/v0")
public class CommonService{

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

  @PostMapping("/listTaskData")
  public List<TaskData> listTaskData(@RequestBody List<TaskData> taskDataList) {
    LOGGER.info("taskDataList.size: {}", taskDataList.size());
    return taskDataList;
  }
}
