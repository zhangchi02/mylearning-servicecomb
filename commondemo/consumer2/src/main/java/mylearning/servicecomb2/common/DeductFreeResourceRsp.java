package mylearning.servicecomb2.common;

import java.util.List;

public class DeductFreeResourceRsp {
  private String retCode;

  private String retDesc;

  private List<SingleFreeResDeductInfo> freeResDeductInfoList;

  public DeductFreeResourceRsp() {
  }

  public DeductFreeResourceRsp(String retCode, String retDesc,
      List<SingleFreeResDeductInfo> freeResDeductInfoList) {
    this.retCode = retCode;
    this.retDesc = retDesc;
    this.freeResDeductInfoList = freeResDeductInfoList;
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

  public void setRedDesc(String retDesc) {
    this.retDesc = retDesc;
  }

  public List<SingleFreeResDeductInfo> getFreeResDeductInfoList() {
    return freeResDeductInfoList;
  }

  public void setFreeResDeductInfoList(
      List<SingleFreeResDeductInfo> freeResDeductInfoList) {
    this.freeResDeductInfoList = freeResDeductInfoList;
  }

  @Override
  public String toString() {
    return "DeductFreeResourceRsp{" +
        "retCode='" + retCode + '\'' +
        ", retDesc='" + retDesc + '\'' +
        ", freeResDeductInfoList=" + freeResDeductInfoList +
        '}';
  }
}
