package mylearning.servicecomb.constract.service;

import mylearning.servicecomb.constract.common2.BaseResponse;
import mylearning.servicecomb.constract.common2.User;

public interface CommonService2 {
  public String sayHello();

  public String duplicateName(String content, String queryRequestId, String headerRequestId);

  public BaseResponse<User> getUser();

  public BaseResponse<User> getUser2();
}
