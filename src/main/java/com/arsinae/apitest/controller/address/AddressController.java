package com.arsinae.apitest.controller.address;

import java.util.Optional;

import com.arsinae.apitest.model.address.Address;
import com.arsinae.apitest.repository.address.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/address")
public class AddressController {
  
  @Autowired
	private AddressRepository addressRepository;

	@PostMapping(path="")
  public @ResponseBody ResponseEntity<Address> addNewAddress (
		@RequestBody Address address
	) {
    address = addressRepository.save(address);
		return new ResponseEntity<Address>(address, HttpStatus.CREATED);
  }

	@GetMapping("")
	public ResponseEntity<Iterable<Address>> getAllAddresses () {
		return new ResponseEntity<Iterable<Address>>(addressRepository.findAll(Sort.by(Sort.Direction.DESC, "id")), HttpStatus.OK);
	}

	@GetMapping("/client/{id}")
	public ResponseEntity<Iterable<Address>> getAllAddressByClient(@RequestParam String id) {
		Iterable<Address> addresses = addressRepository.findAllByClientId(Integer.parseInt(id));
		return new ResponseEntity<Iterable<Address>>(addresses, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Optional<Address>> getAddressById(@RequestParam String id) {
		Optional<Address> address = addressRepository.findById(Integer.parseInt(id));
		if (address.isPresent()) {
			return new ResponseEntity<Optional<Address>>(address, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
