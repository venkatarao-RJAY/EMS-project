package com.Admin.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Admin.entity.User;

@Repository
public interface DeliveryPersonDao extends JpaRepository<User, Integer> {
    
	List<User> findByseller_id(int id);
	
	List<User> findBySellerAndRoleAndStatus(User seller, String role, String status);
}
