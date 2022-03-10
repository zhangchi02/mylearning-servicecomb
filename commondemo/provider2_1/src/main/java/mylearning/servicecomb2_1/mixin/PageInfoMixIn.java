package mylearning.servicecomb2_1.mixin;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(value = {"firstPage", "lastPage"})
@JsonPropertyOrder({"firstPage", "lastPage"})
public abstract class PageInfoMixIn {
  @JsonProperty("isFirstPage")
  abstract boolean isIsFirstPage();

  @JsonProperty("isIsLastPage")
  abstract boolean isIsLastPage();
}
