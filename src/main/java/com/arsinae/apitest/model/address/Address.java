package com.arsinae.apitest.model.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.arsinae.apitest.model.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "addresses")
public class Address {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

	private String name;
	private String city;
	private String postal_code;
	private String country;
  
  @ManyToOne
  @JoinColumn(name="client_id")
  @JsonIgnoreProperties("addresses")
  private Client client;

  public Address() {
  }

  public Address(String name, String city, String postal_code, String country, Integer client_id) {
    this.name = name;
    this.city = city;
    this.postal_code = postal_code;
    this.country = country;
    this.client.setId(client_id);
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return this.postal_code;
  }

  public void setPostalCode(String postal_code) {
    this.postal_code = postal_code;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Client getClient() {
    return this.client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
