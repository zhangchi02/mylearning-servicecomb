package mylearning.servicecomb2_2.exception;

public class CustomizedException extends RuntimeException {
  private int errorCode;

  private String errorData;

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorData() {
    return errorData;
  }

  public void setErrorData(String errorData) {
    this.errorData = errorData;
  }

  public CustomizedException(int errorCode, String errorData) {
    super("customized exception message", null, true, false);
    this.errorCode = errorCode;
    this.errorData = errorData;
  }

  public CustomizedException() {
  }
}
