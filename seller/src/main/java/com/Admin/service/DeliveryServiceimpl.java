package com.Admin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Admin.Dao.DeliveryPersonDao;
import com.Admin.entity.User;

@Service
public class DeliveryServiceimpl implements DeliveryService {
	
	@Autowired
	DeliveryPersonDao dao;

	@Override
	public User adddeliverper(User u) {
			return dao.save(u);
	}

	@Override
	public User findbyid(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<User> findbysellerroleAndStatus(User seller, String role, String status) {
		// TODO Auto-generated method stub
		return dao.findBySellerAndRoleAndStatus(seller, role, status);
	}

	

}
