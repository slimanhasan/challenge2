package com.example.Challenge2.salesDscription;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.example.Challenge2.ProductEntity.Product;
import com.example.Challenge2.Sales.Sales;


// saleDescription is an entity describing transcations of sales
// each sale has quantity of product

@Entity
public class SaleDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@OneToOne
	Sales sale;
	
	@OneToOne
	Product product;
	
	@Min(value = 1)
	int quantity;

	
	public SaleDescription() {
	}

	public SaleDescription(Sales sale, Product product, int quantity) {
		super();
		this.sale = sale;
		this.product = product;
		this.quantity = quantity;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
