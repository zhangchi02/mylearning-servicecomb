package mylearning.servicecomb.pojo.common.sample;

import lombok.Data;

@Data
public class BusinessParam {
  private Long paramId;

  private String applicationUuid;

  private String businessUuid;

  private String paramKey;

  private String paramValue;

  @Override
  public String toString() {
    return "BusinessParam{" +
        "paramId=" + paramId +
        ", applicationUuid='" + applicationUuid + '\'' +
        ", businessUuid='" + businessUuid + '\'' +
        ", paramKey='" + paramKey + '\'' +
        ", paramValue='" + paramValue + '\'' +
        '}';
  }
}
