package com.example.service;

import java.time.LocalDateTime;
 
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import com.example.entity.DeliveryDetails;
import com.example.entity.DeliveryPerson;
  

 
 
@Service
public class DeliveryDetailsService  {
	
	@Autowired
	DeliveryDetails deliveryPersonRepository;
	
	 
	
    private final DeliveryDetails deliveryDetails;

	 
    @Autowired
    public DeliveryDetailsService(DeliveryDetails deliveryPersonRepository) {
        this.deliveryDetails = new DeliveryDetails();
		this.deliveryPersonRepository = deliveryPersonRepository;
    }

     
    public LocalDateTime generateRandomDeliveryDateTime() {
        // Get the current date/time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Generate a random number of days to add (between 1 and 7, for example)
        Random random = new Random();
        int randomDaysToAdd = random.nextInt(7) + 1; // Add between 1 and 7 days
        // Add the random number of days to the current date/time
        return currentDateTime.plusDays(randomDaysToAdd);
    }

 


     
	 
 
	}
 
	 
	 

