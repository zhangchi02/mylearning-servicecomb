package mylearning.servicecomb2_2.dispatcher.entity;

public class TokenInfo {
  private String sessionid;

  public String getSessionid() {
    return sessionid;
  }

  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }

  public TokenInfo(String sessionid) {
    this.sessionid = sessionid;
  }

  public TokenInfo() {
  }
}
