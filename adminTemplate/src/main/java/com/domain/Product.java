package com.domain;


public class Product {

  private Integer pId;
  private String pName;
  private double pPrice;
  private Integer pCategoryId;

  public Integer getpId() {
    return pId;
  }

  public void setpId(Integer pId) {
    this.pId = pId;
  }

  public String getpName() {
    return pName;
  }

  public void setpName(String pName) {
    this.pName = pName;
  }

  public double getpPrice() {
    return pPrice;
  }

  public void setpPrice(double pPrice) {
    this.pPrice = pPrice;
  }

  public Integer getpCategoryId() {
    return pCategoryId;
  }

  public void setpCategoryId(Integer pCategoryId) {
    this.pCategoryId = pCategoryId;
  }

  @Override
  public String toString() {
    return "Product{" +
            "pId=" + pId +
            ", pName='" + pName + '\'' +
            ", pPrice=" + pPrice +
            ", pCategoryId=" + pCategoryId +
            '}';
  }
}
