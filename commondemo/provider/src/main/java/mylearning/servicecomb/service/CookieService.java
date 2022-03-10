package mylearning.servicecomb.service;

import javax.servlet.http.Cookie;

//import org.apache.servicecomb.foundation.vertx.http.VertxClientRequestToHttpServletRequest;
//import org.apache.servicecomb.foundation.vertx.http.VertxClientResponseToHttpServletResponse;
import org.apache.servicecomb.foundation.vertx.http.VertxServerRequestToHttpServletRequest;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "cookieService")
@RequestMapping(path = "/provider/v0")
public class CookieService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CookieService.class);

  @GetMapping("/login")
  public String login(@RequestParam(value = "name", required = true) String name) {
    return name;
  }

  @GetMapping("/getCookie")
  public String getCookie(@RequestParam(name = "name") String name) {
    VertxServerRequestToHttpServletRequest vs = BeanUtils.instantiateClass(VertxServerRequestToHttpServletRequest.class);
    Cookie[] cookies = vs.getCookies();
    for(Cookie cookie:cookies){
      LOGGER.info("get cookie:{}",cookie);
    }
    return name;
  }
}
