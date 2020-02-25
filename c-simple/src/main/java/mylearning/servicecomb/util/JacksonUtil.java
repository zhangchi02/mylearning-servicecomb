package mylearning.servicecomb.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mylearning.servicecomb.common.DeductFreeResourceRsp;
import mylearning.servicecomb.common.SingleFreeResDeductInfo;
import mylearning.servicecomb.common.User;

public class JacksonUtil {
  public static final ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) throws Exception {
    String deductresource = "{\"retCode\":\"0\",\"retDesc\":\"COMMON.BACKGROUND.ERRMSG.SUCCESS\",\"freeResDeductInfoList\":[{\"retCode\":\"0\",\"retDesc\":\"COMMON.BACKGROUND.ERRMSG.SUCCESS\",\"usageId\":\"resourceusage-12731083137-0-0-1109457181\",\"deductUsageValue\":59921041302873870,\"deductMeasureId\":16,\"debtUsageValue\":0,\"debtMeasureId\":16,\"deductDetails\":[{\"productId\":\"00301-193037-0--0\",\"productName\":\"OBS 3AZ存储包 2PB 包月\",\"deductValue\":59921041302873870}]}]}";
////    List<SingleFreeResDeductInfo>
    DeductFreeResourceRsp obj = mapper.readValue(deductresource, DeductFreeResourceRsp.class);
    System.out.println(obj);
//    // 排除json字符串中实体类没有的字段
//    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


//    try {
//      pledgeCertificate = objectMapper.readValue(requestBody, PledgeCertificate.class);
//      pledge = objectMapper.readValue(requestBody, Pledge.class);
//　　Map<String, Object> map = objectMapper.readValue(requestBody, Map.class);
//
//      String writeValueAsString = objectMapper.writeValueAsString(map.get("obligee"));
//      JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Obligee.class);
//      List<Obligee> obligee = objectMapper.readValue(writeValueAsString, javaType);
//    } catch (IOException e) {
//      return “转换错误”;
//    }
//    String singleFreeResDeductInfo = objectMapper.writeValueAsString(map.get("obligee"))
  }

  public static void deserializeUser() throws Exception {
    String json = "[{\"name\":\"a\",\"password\":\"345\"},{\"name\":\"b\",\"password\":\"123\"}]";

//第一种方法
    List<User> list = mapper.readValue(json, new TypeReference<List<User>>() {
    });
    list.stream().forEach(e -> System.out.println(e));

//第二种方法
    JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
    List<User> list2 = mapper.readValue(json, javaType);
    list2.stream().forEach(e -> System.out.println(e));
  }

  /**
   * 获取泛型的Collection Type
   * @param collectionClass 泛型的Collection
   * @param elementClasses 元素类
   * @return JavaType Java类型
   * @since 1.0
   */
  public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
    return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }
}
