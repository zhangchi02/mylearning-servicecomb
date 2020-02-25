package common.common2.common;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="Student2")
public class Student {
  private List<String> carsList;
}
