package com.arsinae.apitest.repository.orders;

import java.sql.Date;
import java.util.List;

import com.arsinae.apitest.model.datecount.DateCount;
import com.arsinae.apitest.model.orders.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

  Iterable<Orders> findAll(Sort by);

  Iterable<Orders> findAllByDate(Date date);

  Page<Orders> findAllByPrice(Float price, Pageable pageable);

  @Query(nativeQuery = true)
  List<DateCount> countOrdersByDate();

  @Query(value = "SELECT 0 AS id, price, date FROM orders WHERE price = ?1 AND date = ?2", nativeQuery = true)
  List<Orders> findAllByPriceAndDate(Float price, Date date);

  Iterable<Orders> findAllByClientId(Integer client_id);
}