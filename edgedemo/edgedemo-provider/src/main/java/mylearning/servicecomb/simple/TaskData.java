package mylearning.servicecomb.simple;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TaskData {
  private String name;
  private int age;
//  @JsonIgnore  // 复杂属性需要标记@JsonIgnore，否则启动时会报错
  private List<Person> children;
}
