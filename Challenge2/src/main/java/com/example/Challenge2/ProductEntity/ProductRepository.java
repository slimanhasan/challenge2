package com.example.Challenge2.ProductEntity;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// repository for products entity

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	// custom query for updating products info
	
	@Transactional
	@Modifying
	@Query(value = "update Product p set p.name = :name , p.description = :description , "
			+ "p.category = :category , p.creationDate = :creationDate where p.id=:id")
	void updateProduct(@Param("name")String name,@Param("description")String description , 
			@Param("category")String category,@Param("creationDate")Date creationDate,
			@Param("id")int id);
	
}
