package com.example.Challenge2.salesDscription;



//   wrapper to wrap products when creating new sales

public class SaleDescriptionResponse {

	int id;
	int quantity;
	
	
	public SaleDescriptionResponse() {
		// TODO Auto-generated constructor stub
	}

	

	public SaleDescriptionResponse(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
