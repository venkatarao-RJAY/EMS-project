package com.Admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Admin.Dao.UserDao;
import com.Admin.entity.User;
@Service
public class UserServiceimpl implements UserService{
	@Autowired
	UserDao dao;
	@Override
	public User add(User u) {
		// TODO Auto-generated method stub
		return dao.save(u) ;
	}

}
