	package com.example.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
	@Data
	public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false, unique = true)
	    private Long id;

	    @NotNull(message = "Product name is required.")
	    @Size(min = 2, max = 30, message = "Product name must be between 2 and 30 characters.")
	    @Column(name = "name", nullable = false)
	    private String name;

	    @NotNull(message = "Product description is required.")
	    @Size(min = 10, max = 1000, message = "Product description must be between 10 and 1000 characters.")
	    @Column(name = "description", nullable = false)
	    private String description;

	    @Column(name = "price", nullable = false)
	    private double price;

	    @Lob
	    @Column(name = "image", nullable = true ,columnDefinition = "LONGBLOB")
	    private byte[] image;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "create_date", nullable = false)
	    private Date createDate;
	    
 	     
	    
	 }
