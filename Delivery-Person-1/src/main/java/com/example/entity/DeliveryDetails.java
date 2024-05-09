	package com.example.entity;
	
	import java.time.LocalDateTime; // Import LocalDateTime

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.PrePersist;
 import lombok.Data;
	
	@Entity
	 @Data
	 @Component
	 public class DeliveryDetails {
	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	
	    private String c_firstName;
	
	    private String c_lastName;
	
	    private String c_emailId;
	
	
	    private Long c_phoneNo;
	
	 private String d_name;
	 
	 private Long d_phone_num;
	 private String d_emailId;
	
 	
	 private LocalDateTime deliveryDateTime;
	 
	    private String orderId;
	 
	// 
	//    @OneToOne
	//    @JoinColumn(name = "address_id")
	//    private Address address;
	    
	     
		/*
		 * @OneToMany(mappedBy = "deliveryPerson") private List<Order> orders;
		 */
	
	    @ManyToOne
	    @JoinColumn(name = "sender_id")  
	    private Sender sender; 
	
	    @ManyToOne
	    @JoinColumn(name = "customer_id")  
	    private Customer customer;  
	    
	    @PrePersist
	    public void setDeliveryDateTime() {
	        // Automatically set delivery date time to one day after order date time
	        this.deliveryDateTime = this.OrderDateTime.plusDays(1);
	    }
	
	    private LocalDateTime OrderDateTime;  
	
	    private String deliveryStatus;
	
	    private String deliveryLocation;

		 

		 
	
	}
