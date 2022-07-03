package com.example.Challenge2.productApi;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Challenge2.ProductEntity.Product;
import com.example.Challenge2.ProductEntity.ProductRepository;


//productApi  is a rest controller to handle requests for products

@RestController
public class productApi {

	
	@Autowired
	ProductRepository productRepo;
	
	
	
	// fetch all products in database
	@GetMapping("/getProducts")
	public ResponseEntity<?> getProducts(){
		
		return  ResponseEntity.ok(productRepo.findAll());
	}
	
	
	// create a new product after validating its information
	@PostMapping("/createProduct")
	public ResponseEntity<?> createProduct(@Valid @ModelAttribute Product p){
	
		productRepo.save(p);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// update an existing product after validating its new info
	
	@PostMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@Valid @ModelAttribute Product p){
		
		productRepo.updateProduct(p.getName(), p.getDescription(), p.getCategory(), p.getCreationDate(), p.getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	
}
