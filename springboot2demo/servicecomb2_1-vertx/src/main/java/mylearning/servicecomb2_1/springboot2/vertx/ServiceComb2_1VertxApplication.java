package mylearning.servicecomb2_1.springboot2.vertx;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableServiceComb
public class ServiceComb2_1VertxApplication {
  public static void main(String[] args) {
//    SpringApplication.run(VertxApplication.class, args);

    SpringApplicationBuilder builder = new SpringApplicationBuilder(ServiceComb2_1VertxApplication.class);
    builder.main(ServiceComb2_1VertxApplication.class).contextClass(AnnotationConfigApplicationContext.class).build().run(args);
  }
}
