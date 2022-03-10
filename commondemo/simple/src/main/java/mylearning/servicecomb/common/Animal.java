package mylearning.servicecomb.common;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Animal {

  private BigDecimal product;
  private List<Dog> dogList;

  public Animal() {
  }

  public Animal(BigDecimal product, List<Dog> dogList) {
    this.product = product;
    this.dogList = dogList;
  }

  public BigDecimal getProduct() {
    return product;
  }

  public void setProduct(BigDecimal product) {
    this.product = product;
  }

  public List<Dog> getDogList() {
    return dogList;
  }

  public void setDogList(List<Dog> dogList) {
    this.dogList = dogList;
  }

//  @JsonIgnore
  public boolean isSuccess(){
    return true;
  }

  @Override
  public String toString() {
    return "Animal{" +
        "product=" + product +
        ", dogList=" + dogList +
        '}';
  }
}
