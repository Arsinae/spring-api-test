package com.arsinae.apitest.orders;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

  Iterable<Orders> findByDate(Date date);

  List<Orders> findAllByPrice(Float price, Pageable pageable);
}