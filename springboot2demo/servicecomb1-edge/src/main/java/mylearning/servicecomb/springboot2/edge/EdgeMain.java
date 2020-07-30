package mylearning.servicecomb.springboot2.edge;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableServiceComb
public class EdgeMain {
  public static void main(String[] args) {
//    SpringApplication.run(EdgeMain.class, args);

    SpringApplicationBuilder builder = new SpringApplicationBuilder(EdgeMain.class);
    builder.main(EdgeMain.class).contextClass(AnnotationConfigApplicationContext.class).build().run(args);
  }
}
