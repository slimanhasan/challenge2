package com.example.Challenge2.ClientEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


// client entity in database
@Entity
public class Client {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@NotBlank
	String fname,lname,mobile;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String fname,String lname,String mobile) {
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "id = "+id+" ,fname= "+fname+" ,lname = "+lname+" ,phone = "+mobile;
	}
	
	
}
