package mylearning.servicecomb.service;

import javax.servlet.http.Part;

import mylearning.servicecomb.common.TaskData;

public interface IUploadJaxRSService {
  String uploadFileWithJaxRSAndObject(Part file, TaskData taskData);
}
