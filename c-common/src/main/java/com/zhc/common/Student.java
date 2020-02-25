package com.zhc.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"aparty", "bparty"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class Student {
  private static final Logger LOGGER = LoggerFactory.getLogger(Student.class);

  private String name;

  private String gender;

  private String cParty;

  private String DParty;

  private String eParty;

  @NotNull(message = "E000505")
  @Pattern(regexp = "^(?!0+)[0-9]{1,31}+$", message = "E000503")
  @JsonProperty(value = "aParty")
  private String AParty;


  @NotNull(message = "E000505")
  @Pattern(regexp = "^(?!0+)[0-9]{1,31}+$", message = "E000503")
  @JsonProperty(value = "bParty")
  private String BParty;

  public void checkparams() {
    if (AParty.equals(BParty)) {
      LOGGER.warn("error");
    }
  }

  /* public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  public String getAParty() {
    return AParty;
  }
  
  public void setAParty(String aParty) {
    AParty = aParty;
  }
  
  public String getBParty() {
    return BParty;
  }
  
  public void setBParty(String bParty) {
    BParty = bParty;
  }*/
}
