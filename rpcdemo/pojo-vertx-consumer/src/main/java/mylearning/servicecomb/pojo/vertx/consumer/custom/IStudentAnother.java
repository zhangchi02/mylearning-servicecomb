package mylearning.servicecomb.pojo.vertx.consumer.custom;

import mylearning.servicecomb.pojo.vertx.consumer.client.StudentAnother;
import mylearning.servicecomb.pojo.common.sample.Student;

public interface IStudentAnother {
  Student getStudent(String name);
  boolean isStudent(StudentAnother studentAnother);
}
