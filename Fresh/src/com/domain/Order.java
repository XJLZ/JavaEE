package com.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

  private String oId;
  private Date iAddtime;
  private String iUsername;
  private String iPhone;
  private String iAddress;
  private String iWay;

  private List<OrderItem> orderItems = new ArrayList<>();

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public Order() {}

  public Order(String oId, Date iAddtime, String iUsername, String iPhone, String iWay) {
      this.oId = oId;
      this.iAddtime = iAddtime;
      this.iUsername = iUsername;
      this.iPhone = iPhone;
      this.iWay = iWay;
  }

  public Order(String oId, Date iAddtime, String iUsername, String iPhone, String iAddress, String iWay) {
      this.oId = oId;
      this.iAddtime = iAddtime;
      this.iUsername = iUsername;
      this.iPhone = iPhone;
      this.iAddress = iAddress;
      this.iWay = iWay;
  }

  public String getOId() {
    return oId;
  }

  public void setOId(String oId) {
    this.oId = oId;
  }


  public Date getIAddtime() {
    return iAddtime;
  }

  public void setIAddtime(Date iAddtime) {
    this.iAddtime = iAddtime;
  }


  public String getIUsername() {
    return iUsername;
  }

  public void setIUsername(String iUsername) {
    this.iUsername = iUsername;
  }


  public String getIPhone() {
    return iPhone;
  }

  public void setIPhone(String iPhone) {
    this.iPhone = iPhone;
  }


  public String getIAddress() {
    return iAddress;
  }

  public void setIAddress(String iAddress) {
    this.iAddress = iAddress;
  }


  public String getIWay() {
    return iWay;
  }

  public void setIWay(String iWay) {
    this.iWay = iWay;
  }

    @Override
    public String toString() {
        return oId + "\t" +iAddtime+ "\t" +iUsername+ "\t" +iPhone+ "\t" +iAddress+ "\t" +iWay;
    }
}
