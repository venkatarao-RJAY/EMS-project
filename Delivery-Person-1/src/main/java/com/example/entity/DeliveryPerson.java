package com.example.entity;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Entity
@Data
@Service
public class DeliveryPerson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 private String d_name;
	 
	 private Long d_phone_num;
	 private String d_emailId;
	
	 private String password;
	  
	 @NotBlank(message = "License number is required")
	    @Pattern(regexp = "^[A-Za-z]{2}\\d{13}[0-9]{2}[0-9]{7}$", message = "Invalid license number format")
	    private String licence;
	 
	 @NotBlank(message = "Vehicle number is required")
	    @Pattern(regexp = "^[A-Za-z]{2}\\d{2}[A-Za-z]{2}\\d{4}$", message = "Invalid vehicle number format")
	    private String vehicleNumber;
	 
	 
}
