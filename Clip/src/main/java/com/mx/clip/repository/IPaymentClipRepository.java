package com.mx.clip.repository;



import org.springframework.data.repository.CrudRepository;


import com.mx.clip.model.PaymentClip;


public interface IPaymentClipRepository extends CrudRepository<PaymentClip, Long> {
	
	
	
	//@Query("select p from Person p where p.forename = ?1.forename and p.surname = ?1.surname")
    //findByName(Name name);
	
	// @Query(
	//	        value= "SELECT * FROM 'payments' WHERE 'user=:user",
	//	        nativeQuery = true
	//	    )
	// PaymentClip findUser(String user);

}



