package mylearning.servicecomb.springboot.vertx.jar.sample;

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
public class DateVO{
//  @ApiModelProperty("月份")
//  //入参格式化
//  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//  //出参格式化
//  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date month;
}
