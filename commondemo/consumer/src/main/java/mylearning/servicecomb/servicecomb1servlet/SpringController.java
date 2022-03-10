package mylearning.servicecomb.servicecomb1servlet;

public interface SpringController {

  String sayHello(String name);

  String queryCommonDataRange(String pathVariable1, String pathVariable2, String pathVariable3, String pathVariable4);
}
