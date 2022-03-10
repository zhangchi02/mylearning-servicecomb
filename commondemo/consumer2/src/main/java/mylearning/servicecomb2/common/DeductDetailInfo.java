package mylearning.servicecomb2.common;

import java.math.BigDecimal;

public class DeductDetailInfo {
  private String productId;

  private String productName;

  private BigDecimal deductValue;

  public DeductDetailInfo() {
  }

  public DeductDetailInfo(String productId, String productName, BigDecimal deductValue) {
    this.productId = productId;
    this.productName = productName;
    this.deductValue = deductValue;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getDeductValue() {
    return deductValue;
  }

  public void setDeductValue(BigDecimal deductValue) {
    this.deductValue = deductValue;
  }

  @Override
  public String toString() {
    return "DeductDetailInfo{" +
        "productId='" + productId + '\'' +
        ", productName='" + productName + '\'' +
        ", deductValue=" + deductValue +
        '}';
  }
}
