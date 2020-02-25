package mylearning.servicecomb.springboot.servlet.jar;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;

@SpringBootApplication(exclude= DispatcherServletAutoConfiguration.class)
@EnableServiceComb
public class SpringBootServletJarApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootServletJarApplication.class,args);
  }
}

