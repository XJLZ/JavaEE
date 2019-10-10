package com.domain;


public class OrderItem {

    private String itemId;
    private String oId;
    private long fId;
    private double oTotalNet;
    private double oTotalprice;


    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public double getoTotalNet() {
        return oTotalNet;
    }

    public void setoTotalNet(double oTotalNet) {
        this.oTotalNet = oTotalNet;
    }

    public String getOId() {
      return oId;
    }

    public void setOId(String oId) {
      this.oId = oId;
    }


    public long getFId() {
      return fId;
    }

    public void setFId(long fId) {
      this.fId = fId;
    }


    public double getOTotalprice() {
      return oTotalprice;
    }

    public void setOTotalprice(double oTotalprice) {
      this.oTotalprice = oTotalprice;
    }

    @Override
    public String toString() {
      return "OrderItem{" +
              "itemId='" + itemId + '\'' +
              ", oId='" + oId + '\'' +
              ", fId=" + fId +
              ", oTotalprice=" + oTotalprice +
              '}';
    }
}
