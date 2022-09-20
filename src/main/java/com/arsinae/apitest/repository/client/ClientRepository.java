package com.arsinae.apitest.repository.client;

import java.util.List;
import java.util.Optional;

import com.arsinae.apitest.model.client.Client;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
  
  List<Client> findAll(Pageable pageable);

  @Query(value = "SELECT c.* FROM client c LEFT JOIN orders o ON o.client_id = c.id WHERE o.id = ?1", nativeQuery = true)
  Optional<Client> findByOrderId(Integer order_id);
}
