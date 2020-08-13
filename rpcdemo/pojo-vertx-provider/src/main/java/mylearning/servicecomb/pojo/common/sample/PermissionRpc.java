package mylearning.servicecomb.pojo.common.sample;

import java.util.List;

public interface PermissionRpc {
  BaseResponse<User> getUser();

  BaseResponse<String> reportPosition(String userId, List<Person> persons);
}
