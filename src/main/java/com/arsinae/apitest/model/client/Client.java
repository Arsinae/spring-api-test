package com.arsinae.apitest.model.client;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.arsinae.apitest.model.orders.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "client")
public class Client {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

	private String name;

  @OneToMany(mappedBy = "client", targetEntity = Orders.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnoreProperties("client")
  private List<Orders> orders;

  @Transient
  private Integer order_count;

  @PostLoad
  private void OnLoad() {
    this.order_count = orders != null ? orders.size() : 0;
  }

  public Client() {
  }

  public Client(String name) {
    this.name = name;
  }

  public Client(String name, List<Orders> orders) {
    this.name = name;
    this.orders = orders;
    this.order_count = orders.size();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Orders> getOrders() {
    return this.orders;
  }

  public void setOrders(List<Orders> orders) {
    this.orders = orders;
  }

  public Integer getOrderCount() {
    return this.order_count;
  }

  public void setOrderCount(Integer order_count) {
    this.order_count = order_count;
  }
}
