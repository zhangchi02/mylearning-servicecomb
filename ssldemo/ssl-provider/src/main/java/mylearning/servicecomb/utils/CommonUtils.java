package mylearning.servicecomb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
