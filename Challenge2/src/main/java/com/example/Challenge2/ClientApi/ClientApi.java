package com.example.Challenge2.ClientApi;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Challenge2.ClientEntity.Client;
import com.example.Challenge2.ClientEntity.ClientRepository;


// ClientApi is a rest controller to handle requests related to client

@RestController
public class ClientApi {

	
	@Autowired
	ClientRepository clientRepo;
	
	
	// fetch all clients in database
	@GetMapping("/getClients")
	public ResponseEntity<?> getClients(){
		
		return ResponseEntity.ok(clientRepo.findAll());
	}
	
	
	// add a new client to database after validating his data
	@PostMapping(value = "/addClient" )
	public ResponseEntity<?> addClient(@RequestBody @Valid Client c){
		
		clientRepo.save(c);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// update an existing client after validating his new data
	@PostMapping("/updateClient")
	public ResponseEntity<?> updateClient(@RequestBody @Valid  Client c){
		clientRepo.updateClient(c.getFname(), c.getLname(),c.getMobile(),c.getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
