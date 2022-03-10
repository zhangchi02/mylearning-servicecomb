package mylearning.servicecomb.common;

import java.math.BigDecimal;

public class Dog {
  private BigDecimal sort;

  private BigDecimal weight;

  private BigDecimal age;

  private BigDecimal price;

  public Dog() {
  }

  public Dog(BigDecimal sort, BigDecimal weight, BigDecimal age, BigDecimal price) {
    this.sort = sort;
    this.weight = weight;
    this.age = age;
    this.price = price;
  }

  public BigDecimal getSort() {
    return sort;
  }

  public void setSort(BigDecimal sort) {
    this.sort = sort;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getAge() {
    return age;
  }

  public void setAge(BigDecimal age) {
    this.age = age;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Dog{" +
        "sort=" + sort +
        ", weight=" + weight +
        ", age=" + age +
        ", price=" + price +
        '}';
  }
}
