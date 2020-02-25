package com.zhc.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"aparty", "bparty","cparty", "dparty", "eparty"})
public abstract class StudentMixIn {
  @JsonProperty("cparty")
  abstract String getCParty();

  @JsonProperty("dparty")
  abstract String getDParty();

  @JsonProperty("eparty")
  abstract String geteParty();
}
