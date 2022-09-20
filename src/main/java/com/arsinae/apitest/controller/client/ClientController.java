package com.arsinae.apitest.controller.client;

import java.util.List;
import java.util.Optional;

import com.arsinae.apitest.model.client.Client;
import com.arsinae.apitest.repository.client.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/client")
public class ClientController {
  
  @Autowired
	private ClientRepository clientRepository;

  @PostMapping(path="")
  public @ResponseBody ResponseEntity<Client> addNewClient (@RequestParam String name) {
    Client client = new Client(name);
    client = clientRepository.save(client);
    return new ResponseEntity<Client>(client, HttpStatus.CREATED);
  }

  @GetMapping("")
	public ResponseEntity<List<Client>> getAllClients () {
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    Pageable pageSet = PageRequest.of(0, 10, sort);
		return new ResponseEntity<List<Client>>(clientRepository.findAll(pageSet), HttpStatus.OK);
	}

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Client>> getClient (@PathVariable String id) {
    Optional<Client> client = clientRepository.findById(Integer.parseInt(id));
    if (client.isPresent()) {
      return new ResponseEntity<Optional<Client>>(client, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
