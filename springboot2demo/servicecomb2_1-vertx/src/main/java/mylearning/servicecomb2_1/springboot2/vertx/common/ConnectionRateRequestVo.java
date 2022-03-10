package mylearning.servicecomb2_1.springboot2.vertx.common;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ConnectionRateRequestVo {
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date selectStartTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date selectEndTime;

  private String startTime;

  private Long timeLength;

  private Long precision;

  public ConnectionRateRequestVo() {
  }

  public ConnectionRateRequestVo(String startTime, Long timeLength, Long precision) {
    this.startTime = startTime;
    this.timeLength = timeLength;
    this.precision = precision;
  }

  public ConnectionRateRequestVo(Date selectStartTime, Date selectEndTime, String startTime, Long timeLength,
      Long precision) {
    this.selectStartTime = selectStartTime;
    this.selectEndTime = selectEndTime;
    this.startTime = startTime;
    this.timeLength = timeLength;
    this.precision = precision;
  }

  public Date getSelectStartTime() {
    return selectStartTime;
  }

  public void setSelectStartTime(Date selectStartTime) {
    this.selectStartTime = selectStartTime;
  }

  public Date getSelectEndTime() {
    return selectEndTime;
  }

  public void setSelectEndTime(Date selectEndTime) {
    this.selectEndTime = selectEndTime;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public Long getTimeLength() {
    return timeLength;
  }

  public void setTimeLength(Long timeLength) {
    this.timeLength = timeLength;
  }

  public Long getPrecision() {
    return precision;
  }

  public void setPrecision(Long precision) {
    this.precision = precision;
  }
}
