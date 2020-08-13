package mylearning.servicecomb.springboot.vertx.jar;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class SpringBootVertxJarApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootVertxJarApplication.class,args);
  }
}
