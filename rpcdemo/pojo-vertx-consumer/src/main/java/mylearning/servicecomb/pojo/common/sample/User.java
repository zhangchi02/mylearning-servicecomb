package mylearning.servicecomb.pojo.common.sample;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
  private String fId;
  private String fUsername;
  private String fPassword;
  private Integer fType;
  private String fMer;
  private List permissionList;

  public User() {
  }

  public User(String fId, String fUsername, String fPassword, Integer fType, String fMer, List permissionList) {
    this.fId = fId;
    this.fUsername = fUsername;
    this.fPassword = fPassword;
    this.fType = fType;
    this.fMer = fMer;
    this.permissionList = permissionList;
  }

  public String getfId() {
    return fId;
  }

  public void setfId(String fId) {
    this.fId = fId;
  }

  public String getfUsername() {
    return fUsername;
  }

  public void setfUsername(String fUsername) {
    this.fUsername = fUsername;
  }

  public String getfPassword() {
    return fPassword;
  }

  public void setfPassword(String fPassword) {
    this.fPassword = fPassword;
  }

  public Integer getfType() {
    return fType;
  }

  public void setfType(Integer fType) {
    this.fType = fType;
  }

  public String getfMer() {
    return fMer;
  }

  public void setfMer(String fMer) {
    this.fMer = fMer;
  }

  public List getPermissionList() {
    return permissionList;
  }

  public void setPermissionList(List permissionList) {
    this.permissionList = permissionList;
  }

  @Override
  public String toString() {
    return "User{" +
        "fId='" + fId + '\'' +
        ", fUsername='" + fUsername + '\'' +
        ", fPassword='" + fPassword + '\'' +
        ", fType=" + fType +
        ", fMer='" + fMer + '\'' +
        ", permissionList=" + permissionList +
        '}';
  }
}
