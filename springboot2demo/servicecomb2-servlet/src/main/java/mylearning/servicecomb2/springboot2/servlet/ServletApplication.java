package mylearning.servicecomb2.springboot2.servlet;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("mylearning.servicecomb2.springboot2")
@EnableServiceComb
public class ServletApplication {
  public static void main(String[] args) {
//    使用内嵌tomcat启动
    SpringApplication.run(ServletApplication.class, args);
//    不使用web方式（内嵌tomcat）启动
//    SpringApplicationBuilder builder = new SpringApplicationBuilder(ServletJarApplication.class);
//    builder.main(ServletJarApplication.class).contextClass(AnnotationConfigApplicationContext.class).build().run(args);
  }
}
