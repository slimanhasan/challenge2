package com.example.Challenge2.salesDscription;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Challenge2.ProductEntity.Product;
import com.example.Challenge2.Sales.Sales;

@Repository
public interface SaleDscriptionRepository extends JpaRepository<SaleDescription, Integer>{

	
	// custom query for udpating quantity if a transaction
	
	@Transactional
	@Modifying
	@Query(value = "update SaleDescription sd set sd.quantity=:quantity where sd.sale=:sale and sd.product=:product")
	void updateQuantity(@Param("quantity")int quantity,@Param("sale")Sales s,@Param("product")Product p);
	
}