package com.dhairya.apidft;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class CustomerModel {

  private int id;
  private String name;
  private String address;
  
  @XmlElement
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}


