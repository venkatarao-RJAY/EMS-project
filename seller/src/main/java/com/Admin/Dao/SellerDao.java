package com.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Admin.entity.User;
import java.util.List;


@Repository
public interface SellerDao extends JpaRepository<User, Integer>{
	
	List<User> findByRole(String role);
List<User> findByEmailId(String emailId);

}
