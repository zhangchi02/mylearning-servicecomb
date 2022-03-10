package mylearning.servicecomb.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import mylearning.servicecomb.common.TaskData;

public interface IUploadSpringMVCService {
  String uploadFileWithSpringMVCAndObject(@RequestPart(name = "fileName") MultipartFile file, TaskData taskData);
}
