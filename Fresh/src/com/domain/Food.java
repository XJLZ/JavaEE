package com.domain;

public class Food {

    private int fId;
    private String fName;
    private double fPrice;
    private double totalNet;



  public double getTotalNet() {
    return totalNet;
  }

  public void setTotalNet(double totalNet) {
    this.totalNet = totalNet;
  }

  public long getFId() {
      return fId;
    }

    public void setFId(int fId) {
      this.fId = fId;
    }


    public String getFName() {
      return fName;
    }

    public void setFName(String fName) {
      this.fName = fName;
    }


    public double getFPrice() {
      return fPrice;
    }

    public void setFPrice(double fPrice) {
      this.fPrice = fPrice;
    }

      @Override
      public String toString() {
        return "  "+fId + "\t" + fName + "\t" + fPrice;
    }
}
