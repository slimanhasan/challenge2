package com.example.Challenge2.Sales;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// sales repository for sales entity

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer>{

	// custom query for updating sale price
	
	@Transactional
	@Modifying
	@Query(value = "update Sales s set s.price=:price where s.id=:id")
	void updatePrice(@Param("price")double price,@Param("id")int id);
	
	
}
