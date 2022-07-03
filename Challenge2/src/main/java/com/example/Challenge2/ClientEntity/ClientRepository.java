package com.example.Challenge2.ClientEntity;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// repository for client entity
@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{

	
	// custom query for update client info	 
	@Transactional
	@Modifying
	@Query(value = "update Client c set c.fname=:fname , c.lname = :lname , c.mobile=:mobile where c.id=:id ")
	void updateClient(@Param("fname")String fname,@Param("lname")String lname,@Param("mobile")String mobile,@Param("id")int id);
	
}
