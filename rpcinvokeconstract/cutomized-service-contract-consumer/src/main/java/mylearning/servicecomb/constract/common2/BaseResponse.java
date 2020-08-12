package mylearning.servicecomb.constract.common2;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
  private int resultCode;
  private int logicCode;
  private String resultMsg;
  private T data;

  public BaseResponse() {
  }

  public BaseResponse(int resultCode, int logicCode, String resultMsg, T data) {
    this.resultCode = resultCode;
    this.logicCode = logicCode;
    this.resultMsg = resultMsg;
    this.data = data;
  }

  public int getResultCode() {
    return resultCode;
  }

  public void setResultCode(int resultCode) {
    this.resultCode = resultCode;
  }

  public int getLogicCode() {
    return logicCode;
  }

  public void setLogicCode(int logicCode) {
    this.logicCode = logicCode;
  }

  public String getResultMsg() {
    return resultMsg;
  }

  public void setResultMsg(String resultMsg) {
    this.resultMsg = resultMsg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "BaseResponse{" +
        "resultCode=" + resultCode +
        ", logicCode=" + logicCode +
        ", resultMsg='" + resultMsg + '\'' +
        ", data=" + data +
        '}';
  }
}

