	package com.example.entity;
	
	 
	 
	import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
	
	@Entity
	@Data
	public class Customer {
 	
 	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	   private int Receiver_id;
	
	   private String c_firstName;
	
	   private String c_lastName;
	
	   private String c_emailId;
	
	   private String password;
	
	   private Long c_phoneNo;
	   
	   private String productname;
	   
	    private LocalDateTime OrderDateTime;
	    private LocalDateTime deliveryDateTime; // Add delivery date and time
	    @NotBlank(message = "order ID is required")
	    @Pattern(regexp = "^[a-zA-Z0-9]+[a-zA-Z0-9]")
	    private String orderId; 
	    
	   @PrePersist
	   public void setDeliveryDateTime() {
	       // Automatically set delivery date time to one day after order date time
	       this.deliveryDateTime = this.OrderDateTime.plusDays(1);
	   }
	   
	   private String deliverylocation;
	   
	
	  
	   @ManyToOne
	   @JoinColumn(name = "sender_id")  
	   @JsonIgnore
	   private Sender sender; 
	    
 
	}