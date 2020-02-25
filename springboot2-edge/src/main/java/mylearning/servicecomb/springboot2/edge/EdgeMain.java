package mylearning.servicecomb.springboot2.edge;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class EdgeMain {
  public static void main(String[] args) {
    SpringApplication.run(EdgeMain.class, args);
  }
}
