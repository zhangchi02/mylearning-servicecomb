package mylearning.servicecomb.pojo.common.sample;

import lombok.Data;

@Data
public class PageVO {
  private String applicationUuid;

  private String businessUuid;

  @Override
  public String toString() {
    return "PageVO{" +
        "applicationUuid='" + applicationUuid + '\'' +
        ", businessUuid='" + businessUuid + '\'' +
        '}';
  }
}
