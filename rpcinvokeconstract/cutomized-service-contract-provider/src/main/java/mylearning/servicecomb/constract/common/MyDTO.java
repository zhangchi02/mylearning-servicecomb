package mylearning.servicecomb.constract.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyDTO {
  @NotNull(message = "stringA不能为空")
  @Pattern(regexp = "^[^\\u4e00-\\u9fa5]+$", message = "不能包含中文字符")
  private String stringA;

  @NotNull(message = "stringB不能为空")
  private String stringB;

  private String stringC;

  @NotNull(message = "intA不能为空")
  private Integer intA;

  private Integer intB;

  private Integer intC;

  @NotNull(message = "booleanA不能为空")
  private Boolean booleanA;

  private Boolean booleanB;

  private Boolean booleanC;

  @NotNull(message = "myObjs不能为空")
  @Valid
  private MyObj[] myObjs;
}
