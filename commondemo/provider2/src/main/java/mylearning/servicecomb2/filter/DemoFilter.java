package mylearning.servicecomb2.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class DemoFilter implements HttpServerFilter {

  private static final Logger LOG = LoggerFactory.getLogger(DemoFilter.class);

  private static final String LET_STRANGER_PASS = "LetStrangerPass";

  @Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    LOG.info("DemoFilter.afterReceiveRequest is invoked.");
    //从请求中取出一个header
    String letStrangerPass = httpServletRequestEx.getHeader(LET_STRANGER_PASS);
    String operationName = invocation.getOperationMeta().getMicroserviceQualifiedName();
    if("provider.commonService.queryTeacher".equalsIgnoreCase(operationName)){
      String teacherName = httpServletRequestEx.getBodyBuffer().toJsonObject().getString("name");
      LOG.info("teacher name: {}.",teacherName);
    }
    if (!StringUtils.isEmpty(letStrangerPass)) {
      //如果此header存在则将它存入到InvocationContext中，InvocationContext可以从上游服务自动传递给下游服务。
      invocation.addContext(LET_STRANGER_PASS, letStrangerPass);
    }
    LOG.info("get cookie from header.");
    String header = httpServletRequestEx.getHeader("Cookie");
    LOG.info("Cookie: {}.", header);
    LOG.info("get cookie from cookies.");
    Cookie[] cookies = httpServletRequestEx.getCookies();
    for (Cookie cookie : cookies) {
      LOG.info("cookie name:{},cookie value:{}", cookie.getName(), cookie.getValue());
    }
    return null;
  }

  @Override
  public void beforeSendResponse(Invocation invocation, HttpServletResponseEx responseEx) {
    LOG.info("DemoFilter beforeSendResponse.");
    String operationName = invocation.getOperationMeta().getMicroserviceQualifiedName();
    if ("provider.commonService.responseHeader2".equals(operationName)) {
      responseEx.setContentType(MediaType.TEXT_HTML);
    }
  }

  @Override
  public int getOrder() {
    return 0;
  }

  //  Filter机制有两个接口，即HttpServerFilter和HttpClientFilter
  //  Filter扩展机制工作于Handler扩展机制的外层
  //  HttpServerFilter在provider端handler前工作
  //  HttpClientFilter在consumer端handler后工作
  //  Filer机制只有全局级别的生效范围

  //  public interface HttpServerFilter {
  //
  //    /**
  //     * HttpServerFilter的优先级
  //     * @return
  //     */
  //    int getOrder();
  //
  //    /**
  //     * 是否启用该Filter，默认启用
  //     * @return
  //     */
  //    default boolean enabled() {
  //      return true;
  //    }
  //    /**
  //      * 微服务作为provider接到请求后，会依次调用各HttpServerFilter的afterReceiveRequest方法进行处理。
  //      * 注意：如果不了解框架的底层逻辑，建议对于 invocation 和 httpServletRequestEx 两个参数只读不写，否则容易导致意料之外的问题。
  //      * @param invocation 包含了本次请求的相关信息
  //      * @param httpServletRequestEx 包含了一些请求的原始信息。例如， 不在服务契约中声明的header是不会存放在Invocation中传递给下游的provider端handler链的，但是在requestEx里我们可以拿到这些header信息
  //      * @return 如果返回null，则该请求还会继续向下执行；否则会将该方法返回的Response作为应答发给调用方，不再执行接下来的请求处理逻辑。
  //      */
  //    Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx);
  //    /**
  //      * 在应答发送给调用方之前，依次调用各HttpServerFilter的beforeSendResponse方法进行处理。默认不处理。
  //      * 注意：如果不了解框架的底层逻辑，建议对于 invocation 和 httpServletResponseEx 两个参数只读不写。
  //      * @param invocation 与afterReceiveRequest方法接收到的invocation相同
  //      * @param httpServletResponseEx 即将发送给调用方的应答。responseEx内可以自定义一些返回的header信息，但建议不要修改body
  //      */
  //    default void beforeSendResponse(Invocation invocation, HttpServletResponseEx httpServletResponseEx){
  //    }
  //  } 

  //  public interface HttpClientFilter{
  //    /**
  //      * HttpClientFilter的优先级
  //      */
  //    int getOrder();
  //    /**
  //      * 是否启用该Filter，默认启用
  //      */
  //    default boolean enabled(){ 
  //      return true; 
  //    }
  //    /**
  //      * 微服务作为consumer发送请求时，会依次调用各HttpClientFilter的beforeSendRequest方法进行处理。
  //      * @param invocation 包含了本次请求的相关信息，包括服务契约相关的信息。
  //      * @param requestEx 本次请求相关的参数信息。可以在这里自定义一些返回的header信息，但建议不要修改body
  //      */
  //    default void beforeSendRequest(Invocation invocation,HttpServletRequestEx requestEx){
  //      
  //    }
  //    /**
  //      * 接收到服务端的应答后，依次调用各HttpClientFilter的afterReceiveResponse方法进行处理。
  //      * 
  //      * @param invocation 与 afterReceiveRequest 方法接收到的invocation相同
  //      * @param responseEx 包含了一些应答的原始数据信息，如header等
  //      * @return 如果返回null，则继续调用下一个HttpClientFilter；否则会将该方法返回的Response作为应答返回给业务逻辑。
  //      */
  //    Response afterReceiveResponse(Invocation invocation,HttpServletResponseEx responseEx); 
  //  }
}
