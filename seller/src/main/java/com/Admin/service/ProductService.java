package com.Admin.service;

import java.util.List;

import com.Admin.entity.Product;

public interface ProductService {

	Product addpro(Product p);
	
	Product update(Product p);
	
	boolean delete(int id);
	List<Product> fetchall();
	
	Product findbyid(int id);
}
