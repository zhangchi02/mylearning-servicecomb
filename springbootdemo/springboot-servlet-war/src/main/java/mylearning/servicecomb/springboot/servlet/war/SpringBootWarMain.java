package mylearning.servicecomb.springboot.servlet.war;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;

@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
@EnableServiceComb
public class SpringBootWarMain {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootWarMain.class, args);
  }
}
