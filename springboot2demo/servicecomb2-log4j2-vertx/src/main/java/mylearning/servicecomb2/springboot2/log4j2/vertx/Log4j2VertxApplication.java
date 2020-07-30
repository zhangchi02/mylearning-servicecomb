package mylearning.servicecomb2.springboot2.log4j2.vertx;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableServiceComb
public class Log4j2VertxApplication {
  public static void main(String[] args) {
//    SpringApplication.run(SpringBoot2VertxJarApplication.class, args);

    SpringApplicationBuilder builder = new SpringApplicationBuilder(Log4j2VertxApplication.class);
    builder.main(Log4j2VertxApplication.class).contextClass(AnnotationConfigApplicationContext.class).build().run(args);
  }
}
