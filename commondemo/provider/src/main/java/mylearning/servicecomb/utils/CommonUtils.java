package mylearning.servicecomb.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.paas.foundation.auth.signer.utils.SignerUtils;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

public class CommonUtils {
  private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);

  public static String getDynamicStringProperty(String key, String defaultValue) {
    DynamicStringProperty dynamicStringProperty =
        DynamicPropertyFactory.getInstance().getStringProperty(key, defaultValue, () -> LOG.info("获取动态配置"));
    LOG.info("key:{},value:{}.", key, dynamicStringProperty.getValue());
    return dynamicStringProperty.getValue();
  }

  public static void main(String[] args) throws Exception {
    Map<String, String> aksk = new HashMap<>();
    Map<String, String> signHeader = new HashMap<>();
    signHeader.put("X-Service-ShaAKSK", aksk.computeIfAbsent("WGLB0AUHBKIKRLIJVNTE", key -> {
      try {
        return SignerUtils.sha256Encode("pUo2LfRkty8Z37XgleziBBi9D57857d5dt4wB5lS", key);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }));
    signHeader.put("X-Service-Project", URLEncoder.encode("sa-brazil-1", StandardCharsets.UTF_8.name()));
    System.out.println(signHeader.get("X-Service-ShaAKSK"));
    System.out.println(signHeader.get("X-Service-Project"));
  }
}
