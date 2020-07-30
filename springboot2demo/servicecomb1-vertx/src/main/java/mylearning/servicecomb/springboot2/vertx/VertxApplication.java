package mylearning.servicecomb.springboot2.vertx;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableServiceComb
public class VertxApplication {
  public static void main(String[] args) {
//    SpringApplication.run(VertxApplication.class, args);

    SpringApplicationBuilder builder = new SpringApplicationBuilder(VertxApplication.class);
    builder.main(VertxApplication.class).contextClass(AnnotationConfigApplicationContext.class).build().run(args);
  }
}
