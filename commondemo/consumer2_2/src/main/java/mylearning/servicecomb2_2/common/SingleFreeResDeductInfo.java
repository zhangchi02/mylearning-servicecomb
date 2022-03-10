package mylearning.servicecomb2_2.common;

import java.math.BigDecimal;
import java.util.List;

public class SingleFreeResDeductInfo {
  private String retCode;

  private String retDesc;

  private String usageId;

  private BigDecimal deductUsageValue;

  private Integer deductMeasureId;

  private BigDecimal debtUsageValue;

  private Integer debtMeasureId;

  private BigDecimal capacityValue;

  private Integer capacityMeasureId;

  private List<DeductDetailInfo> deductDetails;

  public SingleFreeResDeductInfo() {
  }

  public SingleFreeResDeductInfo(String retCode, String retDesc, String usageId, BigDecimal deductUsageValue,
      Integer deductMeasureId, BigDecimal debtUsageValue, Integer debtMeasureId, BigDecimal capacityValue,
      Integer capacityMeasureId, List<DeductDetailInfo> deductDetails) {
    this.retCode = retCode;
    this.retDesc = retDesc;
    this.usageId = usageId;
    this.deductUsageValue = deductUsageValue;
    this.deductMeasureId = deductMeasureId;
    this.debtUsageValue = debtUsageValue;
    this.debtMeasureId = debtMeasureId;
    this.capacityValue = capacityValue;
    this.capacityMeasureId = capacityMeasureId;
    this.deductDetails = deductDetails;
  }

  public String getRetCode() {
    return retCode;
  }

  public void setRetCode(String retCode) {
    this.retCode = retCode;
  }

  public String getRetDesc() {
    return retDesc;
  }

  public void setRetDesc(String retDesc) {
    this.retDesc = retDesc;
  }

  public String getUsageId() {
    return usageId;
  }

  public void setUsageId(String usageId) {
    this.usageId = usageId;
  }

  public BigDecimal getDeductUsageValue() {
    return deductUsageValue;
  }

  public void setDeductUsageValue(BigDecimal deductUsageValue) {
    this.deductUsageValue = deductUsageValue;
  }

  public Integer getDeductMeasureId() {
    return deductMeasureId;
  }

  public void setDeductMeasureId(Integer deductMeasureId) {
    this.deductMeasureId = deductMeasureId;
  }

  public BigDecimal getDebtUsageValue() {
    return debtUsageValue;
  }

  public void setDebtUsageValue(BigDecimal debtUsageValue) {
    this.debtUsageValue = debtUsageValue;
  }

  public Integer getDebtMeasureId() {
    return debtMeasureId;
  }

  public void setDebtMeasureId(Integer debtMeasureId) {
    this.debtMeasureId = debtMeasureId;
  }

  public BigDecimal getCapacityValue() {
    return capacityValue;
  }

  public void setCapacityValue(BigDecimal capacityValue) {
    this.capacityValue = capacityValue;
  }

  public Integer getCapacityMeasureId() {
    return capacityMeasureId;
  }

  public void setCapacityMeasureId(Integer capacityMeasureId) {
    this.capacityMeasureId = capacityMeasureId;
  }

  public List<DeductDetailInfo> getDeductDetails() {
    return deductDetails;
  }

  public void setDeductDetails(List<DeductDetailInfo> deductDetails) {
    this.deductDetails = deductDetails;
  }

  @Override
  public String toString() {
    return "SingleFreeResDeductInfo{" +
        "retCode='" + retCode + '\'' +
        ", retDesc='" + retDesc + '\'' +
        ", usageId='" + usageId + '\'' +
        ", deductUsageValue=" + deductUsageValue +
        ", deductMeasureId=" + deductMeasureId +
        ", debtUsageValue=" + debtUsageValue +
        ", debtMeasureId=" + debtMeasureId +
        ", capacityValue=" + capacityValue +
        ", capacityMeasureId=" + capacityMeasureId +
        ", deductDetails=" + deductDetails +
        '}';
  }
}
