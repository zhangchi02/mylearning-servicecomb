package mylearning.servicecomb.common;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DateVO extends DateBO{
  @ApiModelProperty("月份")
//  @DateTimeFormat 注解的 pattern 属性值指定的日期时间格式并不是将要转换成的日期格式，这个指定的格式是和传入的参数对应的
//  假如注解为：@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss"),则传入的参数应该是这样的：2018/08/02 22:05:55，否则会抛出异常。
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//  出参格式化
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date month;
}
