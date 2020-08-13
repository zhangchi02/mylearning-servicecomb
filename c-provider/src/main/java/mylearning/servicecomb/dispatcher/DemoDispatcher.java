package mylearning.servicecomb.dispatcher;

import org.apache.servicecomb.transport.rest.vertx.VertxRestDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class DemoDispatcher{
//public class DemoDispatcher extends VertxRestDispatcher {
//  private static final Logger LOGGER = LoggerFactory.getLogger(DemoDispatcher.class);
//
//  @Override
//  public boolean enabled() {
//    return true;
//  }
//
//  @Override
//  public void init(Router router) {
//    super.init(router);
//  }
//
//  @Override
//  protected void onRequest(RoutingContext context) {
////    Cookie cookie1 = Cookie.cookie("expiretime1", "20191025T023619Z");
////    Cookie cookie2 = Cookie.cookie("rtCiphertext",
////        "hTIDSRu8i0IXxRp+wvINXHXXXXXXXXXXXUHXzJ7PXXXXXXXXXXXrl+At3yjJUbf5iYwrimvb0f2U/LD8O4rS89swxRJ4uCCJ8YyvcSSm/zSLJdOmWFNVkEuZ4rAgUI+hNbC4z4LTr7u6rqWxFbjFNP32VFlarKBSfMXOKs7bXEoy6naCnP/6NyLjzbxg");
////    Cookie cookie3 = Cookie.cookie("createtime", "20191025T013619Z");
////    Cookie cookie4 = Cookie.cookie("accesstoken",
////        "CF2yX6NOTPJEiDuOVujXXXXXXXXXXg672ufs0wkABYuMiSHEOCdUbBQps1KBDztnzkpn464/X7rI6PVozij4CTQoj1aL/rBlfmu4A+8YMmf7oMGUA==");
////    Cookie cookie5 = Cookie.cookie("siteID", "11");
////    Cookie cookie6 = Cookie.cookie("x-uid", "890086000000000800");
////
////    context.addCookie(cookie1);
////    context.addCookie(cookie2);
////    context.addCookie(cookie3);
////    context.addCookie(cookie4);
////    context.addCookie(cookie5);
////    context.addCookie(cookie6);
//    super.onRequest(context);
//  }
}
