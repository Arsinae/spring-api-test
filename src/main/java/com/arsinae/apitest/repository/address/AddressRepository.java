package com.arsinae.apitest.repository.address;

import java.util.List;

import com.arsinae.apitest.model.address.Address;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

  public List<Address> findAll(Sort by);

  Iterable<Address> findAllByClientId(Integer client_id);
}
