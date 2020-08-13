package mylearning.servicecomb.pojo.vertx.consumer.service;

import org.apache.servicecomb.provider.pojo.Invoker;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import mylearning.servicecomb.pojo.vertx.consumer.client.StudentAnother;
import mylearning.servicecomb.pojo.vertx.consumer.custom.IStudentAnother;
import mylearning.servicecomb.pojo.common.sample.IStudent;
import mylearning.servicecomb.pojo.common.sample.Student;

@RpcSchema(schemaId = "studentService")
public class StudentService {
  private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

  @RpcReference(microserviceName = "provider", schemaId = "student")
  private IStudent iStudent;

  private IStudent studentProxy = Invoker.createProxy("provider", "student", IStudent.class);

  private IStudentAnother studentAnotherProxy = Invoker.createProxy("provider", "student", IStudentAnother.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  public Student testStudent() {
    LOG.info("invoke method getStudent.");
    return iStudent.getStudent("Student");
  }

  public Student testStudentByStudentProxy() {
    LOG.info("invoke method getStudentByStudentProxy.");
    return studentProxy.getStudent("Student");
  }

  public Student testStudentByStudentAnotherProxy() {
    LOG.info("invoke method getStudentByStudentAnotherProxy.");
    return studentAnotherProxy.getStudent("StudentAnother");
  }

  public Student testStudentByTem() {
    LOG.info("invoke method getStudentByTem.");
    ResponseEntity response = restTemplate
        .postForEntity("cse://provider/StudentImpl/getStudent", "Java", Student.class);
    return (Student) response.getBody();
  }

  public StudentAnother testStudentAnotherByTem() {
    LOG.info("invoke method getStudentAnotherByTem.");
    ResponseEntity response = restTemplate
        .postForEntity("cse://provider/StudentImpl/getStudent", "Java", StudentAnother.class);
    return (StudentAnother) response.getBody();
  }

  public boolean testIsStudent() {
    LOG.info("invoke method isStudent.");
    Student student = new Student("Java", 23);
    return iStudent.isStudent(student);
  }

  public boolean testIsStudentByStudentProxy() {
    LOG.info("invoke method isStudentByStudentProxy.");
    Student student = new Student("Java", 23);
    return studentProxy.isStudent(student);
  }

  public boolean testIsStudentByStudentAnotherProxy() {
    LOG.info("invoke method isStudentByStudentAnotherProxy.");
    StudentAnother studentAnother = new StudentAnother("Java", 23);
    return studentAnotherProxy.isStudent(studentAnother);
  }

  public boolean testIsStudentByTem() {
    LOG.info("invoke method isStudentByTem.");
    Student student = new Student("Java", 23);
    ResponseEntity response = restTemplate
        .postForEntity("cse://provider/StudentImpl/isStudent", student, Boolean.class);
    return (boolean) response.getBody();
  }

  public boolean testIsStudentAnotherByTem() {
    LOG.info("invoke method isStudentAnotherByTem.");
    StudentAnother studentAnother = new StudentAnother("Java", 23);
    ResponseEntity response = restTemplate
        .postForEntity("cse://provider/StudentImpl/isStudent", studentAnother, Boolean.class);
    return (boolean) response.getBody();
  }
}
