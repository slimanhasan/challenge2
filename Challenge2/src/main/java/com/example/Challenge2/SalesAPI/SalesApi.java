package com.example.Challenge2.SalesAPI;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Challenge2.ClientEntity.ClientRepository;
import com.example.Challenge2.ProductEntity.Product;
import com.example.Challenge2.ProductEntity.ProductRepository;
import com.example.Challenge2.Sales.Sales;
import com.example.Challenge2.Sales.SalesRepository;
import com.example.Challenge2.salesDscription.SaleDescription;
import com.example.Challenge2.salesDscription.SaleDscriptionRepository;
import com.example.Challenge2.salesDscription.SaleDescriptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SalesApi is a rest controller to handle requests related to sales and transactions

@RestController
public class SalesApi {

	Logger logger=LoggerFactory.getLogger(SalesApi.class);
	
	@Autowired
	SalesRepository salesRepo;
	
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	SaleDscriptionRepository SaleDscriptionRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	
	// fetch all sales in database
	@GetMapping("/getSales")
	public ResponseEntity<?> getSales(){
		
		return  ResponseEntity.ok(salesRepo.findAll());
	}
	
	
	
	// create new sale with it's transactions and insert it into database after validating data and adding info to the logger
	
	/* example of request data
	  
	 	{
	        "creationDate":"2022-07-03",
	        "c":1,
	        "seller":"company 1",
	        "price":"1000",
	        "products":[
	            {
	                "id":"2",
	                "quantity":"10"
	            },
	            {
	            	"id":"3",
	            	"quantity":"5"
	            }
	        ]
	    
  
		}
	 */
	@PostMapping("/createSale")
	public ResponseEntity<?> createSale(@RequestBody JSONObject orderInfo) throws Exception{		// I'm sending raw data in request and receiving it as a JSON object
		
		int clientId=Integer.parseInt(orderInfo.getAsString("c"));		//orderInfo is a (key - value) map
		String creationDate= orderInfo.getAsString("creationDate");
		String seller = orderInfo.getAsString("seller");
		double price=Double.parseDouble(orderInfo.getAsString("price"));
		
		Sales s=new Sales();											// first we create the sale and then we add  transaction details such as products ids and quantities
		s.setC(clientRepo.findById(clientId).get());
		s.setCreationDate(new SimpleDateFormat("yyyy-mm-dd").parse(creationDate));
		s.setSeller(seller);
		s.setPrice(price);
		
		s=salesRepo.save(s);
		
		@SuppressWarnings("unchecked")
		List<Object>l=(List<Object>) orderInfo.get("products");
		ObjectMapper mapper=new ObjectMapper();					// objectMapper is to convert mapped data to a saleDescriptionResponse
		List<SaleDescriptionResponse>products=l.stream()
				.map(object->mapper.convertValue(object, SaleDescriptionResponse.class))
				.collect(Collectors.toList());
		
		for(SaleDescriptionResponse i:products) {
			
			Product p =productRepo.findById(i.getId()).get();
			
			SaleDescription sd=new SaleDescription(s, p, i.getQuantity());		// build each transcation
			
			SaleDscriptionRepo.save(sd);									// add transaction to db
			
		}
		
		logger.info("new sale created with the following details:\n"
				+"id = "+s.getId() +" , client id = " + s.getC().getId() + " , seller name = " + s.getSeller() +
				" , price = " + s.getPrice() );
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// update price or quantity or both for an existing sale 
	
	/*
	 * 
		 example of request data
		 {
		    "saleId":"5",
		    "price":"700",
		    "productId":"2",
		    "quantity":"7" 
		  
		 }
	 
	 
	 */
	@PostMapping("/updateSale")
	public ResponseEntity<?> updateSale(@RequestBody JSONObject order){
		
		int saleId=Integer.parseInt(order.getAsString("saleId"));	//  getting saleId from raw data
		Sales s=salesRepo.findById(saleId).get();					// getting sale with id=saleId
		
		
		if(order.containsKey("price")) {						// we can update only price of the sale by only specifying saleId and new price 		
			double newPrice=Double.parseDouble(order.getAsString("price"));
			salesRepo.updatePrice(newPrice, saleId);
		}
		
		
		if(order.containsKey("quantity")) {						// we can update only quantity  of the sale by only specifying saleId and new quantity
			int newQuantity=Integer.parseInt(order.getAsString("quantity"));
			int productId=Integer.parseInt(order.getAsString("productId"));
			
			Product p=productRepo.findById(productId).get();
			
			SaleDscriptionRepo.updateQuantity(newQuantity, s , p);
		}
		
		// if we specified both price and quantity then both (if conditions) will be true
		
		logger.info("sale with id = " + saleId + " has been updated\n");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
