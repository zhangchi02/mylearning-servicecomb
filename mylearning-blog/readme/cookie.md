> - 当Cookie是以`Cookie:Cookie_5={"key1":"value1","key2":"value2","key3":"value3"}`格式传递，可以通过**httpServletRequestEx.getHeader("Cookie")**获得
> - 当Cookie是以`Cookie:key=value`格式传递，可以通过**httpServletRequestEx.getCookies()**获得

```java
@Override
  public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx httpServletRequestEx) {
    LOG.info("DemoFilter.afterReceiveRequest is invoked.");
    //从请求中取出一个header
    String letStrangerPass = httpServletRequestEx.getHeader(LET_STRANGER_PASS);
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
```  

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/08/185119b9o01kxnzcwwkqks.png)


> 关于如何扩展**org.apache.servicecomb.common.rest.filter.HttpServerFilter**，请参考[21天转型微服务实战营](https://education.huaweicloud.com:8443/courses/course-v1:HuaweiX+CBUCNXP012+Self-paced/about)**DAY7 CSE实战之框架扩展机制**