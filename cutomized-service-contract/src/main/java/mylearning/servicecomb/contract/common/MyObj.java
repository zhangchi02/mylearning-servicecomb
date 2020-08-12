package mylearning.servicecomb.contract.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyObj {
  @NotNull(message = "objA不能为空")
  @Pattern(regexp = "^[^\\u4e00-\\u9fa5]+$", message = "objA不能包含中文字符")
  private String objA;
}
