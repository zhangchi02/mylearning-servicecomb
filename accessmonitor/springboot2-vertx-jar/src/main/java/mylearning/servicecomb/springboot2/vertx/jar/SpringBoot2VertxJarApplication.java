package mylearning.servicecomb.springboot2.vertx.jar;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class SpringBoot2VertxJarApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2VertxJarApplication.class, args);
  }
}
