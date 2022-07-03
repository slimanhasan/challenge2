package com.example.Challenge2.ProductEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

// product entity
@Entity
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@NotBlank
	String name,description,category;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date creationDate;

	
	public Product() {
	}
	
	public Product(String name,String description,String category,Date creationDate) {
		this.name=name;
		this.description=description;
		this.category=category;
		this.creationDate=creationDate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String describtion) {
		this.description = describtion;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "id = " + id + " ,name = " + name + " category = "+category + " ,description = "+description;
	}
	
	
	
	
}
