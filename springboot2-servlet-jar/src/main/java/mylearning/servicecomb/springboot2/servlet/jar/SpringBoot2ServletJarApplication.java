package mylearning.servicecomb.springboot2.servlet.jar;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class SpringBoot2ServletJarApplication {
  public static void main(String[] args) {
//    使用内嵌tomcat启动
    SpringApplication.run(SpringBoot2ServletJarApplication.class, args);
//    不使用web方式（内嵌tomcat）启动
//    SpringApplicationBuilder builder = new SpringApplicationBuilder(ServletJarApplication.class);
//    builder.main(ServletJarApplication.class).contextClass(AnnotationConfigApplicationContext.class).build().run(args);
  }
}
