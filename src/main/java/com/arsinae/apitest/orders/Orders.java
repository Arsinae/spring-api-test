package com.arsinae.apitest.orders;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

	private Float price;

	private Date date;

	public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
