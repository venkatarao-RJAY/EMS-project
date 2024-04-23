package com.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Admin.entity.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{

}
