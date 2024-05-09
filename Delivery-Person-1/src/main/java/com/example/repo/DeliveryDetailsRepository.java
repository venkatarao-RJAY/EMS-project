package com.example.repo;

 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Customer;
import com.example.entity.DeliveryDetails;
import com.example.entity.DeliveryPerson;
import com.example.entity.Sender;
@Repository
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails, Integer>{

 
	Customer save(Customer receiver);
	@Query("SELECT s FROM Sender s WHERE s.orderId = :orderId")
	Sender findByOrderId2(String orderId);

 	@Query("SELECT dp FROM Customer dp WHERE dp.sender.orderId = :orderId")
	List<Customer> findByOrderId(String orderId);
	
	 
	@Query("SELECT dp FROM DeliveryDetails dp WHERE dp.sender.orderId = :orderId")
	List<DeliveryDetails> findByOrderId1(String orderId);
	Sender save(Sender sender);
	DeliveryPerson save(DeliveryPerson deliveryPerson);

 
 

}
