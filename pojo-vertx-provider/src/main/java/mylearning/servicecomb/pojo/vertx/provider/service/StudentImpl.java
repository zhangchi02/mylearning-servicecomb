package mylearning.servicecomb.pojo.vertx.provider.service;

import org.apache.servicecomb.provider.pojo.RpcSchema;

import mylearning.servicecomb.pojo.common.sample.IStudent;
import mylearning.servicecomb.pojo.common.sample.Student;

@RpcSchema(schemaId = "student")
public class StudentImpl implements IStudent {
  @Override
  public Student getStudent(String name){
    Student student = new Student(name,23);
    return student;
  }

  @Override
  public boolean isStudent(Student student){
    if(student!=null){
      return true;
    }
    return false;
  }
}
