package com.arsinae.apitest.orders;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

  @Query(value = "SELECT * FROM orders WHERE date = ?1", nativeQuery = true)
  Iterable<Orders> findByDate(Date date);
}