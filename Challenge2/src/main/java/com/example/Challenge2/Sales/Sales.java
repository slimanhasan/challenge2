package com.example.Challenge2.Sales;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Challenge2.ClientEntity.Client;
import com.sun.istack.NotNull;


// sales entity
@Entity
public class Sales {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(updatable = false,nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	Date creationDate;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	Client c;
	
	@Column(updatable = false)
	String seller;
	
	@Min(value = 1)
	double price;

	
	public Sales() {
	}
	public Sales(Date creationDate, Client c, String seller,double price) {
		this.creationDate = creationDate;
		this.c = c;
		this.seller = seller;
		this.price=price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
}
