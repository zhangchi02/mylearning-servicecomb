package mylearning.servicecomb.common;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
  @NotNull
  private String name;

  @NotNull
  @Min(0)
  @Max(150)
  private int age;

  @NotNull
  @Min(0)
  @Max(2000)
  @JsonProperty("related_edge_num")
  @Valid
  private Integer relatedEdgeNum = null;

  @DefaultValue(value = "ParamA")
  private String paramA;

  private int paramB;

  @NotNull
  @Min(0L)
  @Max(2000L)
  @JsonProperty("edge_num")
  @Valid
  private Integer edgeNum;
}
