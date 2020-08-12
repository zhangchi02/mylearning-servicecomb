package mylearning.servicecomb.constract.service;

import mylearning.servicecomb.constract.common.BaseResponse;
import mylearning.servicecomb.constract.common.User;

public interface CommonService {
  String sayHello();

  String duplicateName(String content, String queryRequestId, String headerRequestId);

  BaseResponse<User> getUser();

  BaseResponse<User> getUser2();
}
