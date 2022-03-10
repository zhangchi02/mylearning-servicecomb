package mylearning.servicecomb2_1.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.servicecomb.common.rest.filter.HttpClientFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class DemoClientFilter implements HttpClientFilter {
  private static final Logger LOG = LoggerFactory.getLogger(DemoClientFilter.class);

  @Override
  public boolean enabled() {
    return true;
  }

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public void beforeSendRequest(Invocation invocation, HttpServletRequestEx requestEx) {
    LOG.info("DemoClientFilter beforeSendRequest");
  }

  @Override
  public Response afterReceiveResponse(Invocation invocation, HttpServletResponseEx responseEx) {
    int status = responseEx.getStatusType().getStatusCode();
    String operationName = invocation.getOperationMeta().getMicroserviceQualifiedName();
    String endpoint = invocation.getEndpoint().toString();

//    Hasher hasher = Hashing.sha256().newHasher();
//    byte[] bytes = responseEx.getBodyBytes();
//    if (bytes != null) {
//      hasher.putBytes(bytes, 0, responseEx.getBodyBytesLength());
//    }
//    Object obj = unserializeObj(bytes);

    LOG.info("DemoClientFilter afterReceiveResponse");
    return null;
  }

  public static Object unserializeObj(byte[] bytes) {
    ByteArrayInputStream byteInputStream = null;
    ObjectInputStream inputStream = null;
    try {
      byteInputStream = new ByteArrayInputStream(bytes);
      inputStream = new ObjectInputStream(byteInputStream);
      return inputStream.readObject();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if (null != byteInputStream) {
        try {
          byteInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      }
      if (null != inputStream) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      }
    }
  }
}
