package mylearning.servicecomb.springboot2.war;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableServiceComb
public class SpringBoot2WarApplication extends SpringBootServletInitializer {
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringBoot2WarApplication.class);
  }
  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2WarApplication.class);
  }
}
