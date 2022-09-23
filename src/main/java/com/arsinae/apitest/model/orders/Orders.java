package com.arsinae.apitest.model.orders;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.arsinae.apitest.model.client.Client;
import com.arsinae.apitest.model.datecount.DateCount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
@SqlResultSetMapping(
  name="dateCountMapping",
  classes = @ConstructorResult(
    targetClass = DateCount.class,
    columns = {
      @ColumnResult(name = "date", type = String.class),
      @ColumnResult(name = "count", type = Long.class)
    }
  )
)
@NamedNativeQuery(
  name = "Orders.countOrdersByDate",
  resultClass = DateCount.class,
  resultSetMapping = "dateCountMapping",
  query = "SELECT o.date, COUNT(o.date) AS count FROM orders AS o GROUP BY o.date"
)
public class Orders {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

	private Float price;
	private Date date;

  @ManyToOne
  @JoinColumn(name="client_id")
  @JsonIgnoreProperties("orders")
  private Client client;

  public Orders() {
  }

  public Orders(Float price, Date date, Integer client_id) {
    this.price = price;
    this.date = date;
    this.client.setId(client_id);
  }

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

  public Client getClient() {
    return this.client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
