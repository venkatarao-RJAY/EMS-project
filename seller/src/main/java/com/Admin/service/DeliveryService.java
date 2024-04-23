package com.Admin.service;

import java.util.List;

import com.Admin.entity.User;

public interface DeliveryService {
	
	User adddeliverper(User u);
	
	User findbyid(int id);
	
	List<User> findbysellerroleAndStatus( User seller,String role,String status);
	/*
	 * 
	 * 
	 * User update(User u);
	 * 
	 * boolean delete(int id);
	 * 
	 *
	 * 
	 * List<User> findbyrole(String role);
	 */

}
