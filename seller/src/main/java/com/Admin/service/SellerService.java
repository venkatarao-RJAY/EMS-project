package com.Admin.service;

import java.util.List;

import com.Admin.entity.User;

public interface SellerService {
	User addSeller(User u);
	List<User> fetchall(String role);
	User update(User user);
	boolean deletebyid(int id);
	List<User> findbyemailid(String email);

}
