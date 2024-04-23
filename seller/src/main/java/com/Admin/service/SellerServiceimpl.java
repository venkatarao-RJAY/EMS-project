package com.Admin.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Admin.Dao.SellerDao;
import com.Admin.entity.User;

@Service
public class SellerServiceimpl implements SellerService {
	@Autowired
	SellerDao dao;

	@Override
	public User addSeller(User u) {
		// TODO Auto-generated method stub
		return dao.save(u);
	}

	@Override
	public User update(User c) {
		// TODO Auto-generated method stub
		return dao.save(c);
	}

	@Override
	public List<User> fetchall(String role) {

		return dao.findByRole(role);
	}

	@Override
	public boolean deletebyid(int id) {
		// TODO Auto-generated method stub
		if (dao.existsById(id)) {
			dao.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public List<User> findbyemailid(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmailId(email);
	}

	

}
