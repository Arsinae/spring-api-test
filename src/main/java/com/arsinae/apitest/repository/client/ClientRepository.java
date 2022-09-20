package com.arsinae.apitest.repository.client;

import java.util.List;

import com.arsinae.apitest.model.client.Client;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
  
  List<Client> findAll(Pageable pageable);

}
