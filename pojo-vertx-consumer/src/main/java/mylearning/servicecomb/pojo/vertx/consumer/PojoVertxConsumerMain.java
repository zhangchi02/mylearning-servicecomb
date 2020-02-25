package mylearning.servicecomb.pojo.vertx.consumer;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

import mylearning.servicecomb.pojo.vertx.consumer.service.ComputeService;
import mylearning.servicecomb.pojo.vertx.consumer.service.HelloService;
import mylearning.servicecomb.pojo.vertx.consumer.service.StudentService;
import mylearning.servicecomb.pojo.vertx.consumer.service.UserService;

public class PojoVertxConsumerMain {

  public static void main(String[] args) throws Exception {
    BeanUtils.init();
    
//    System.out.println(HelloService.sayHi("Java"));
//    System.out.println(ComputeService.add(1, 1));
//    System.out.println(UserService.func("Java", 23));
//    System.out.println(StudentService.isStudent());
//    System.out.println(StudentService.getStudent());
//    System.out.println(StudentService.getStudentAnother());
  }

}
