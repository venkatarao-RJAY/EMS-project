package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.dto.UserLoginRequest;
import com.example.entity.User;


@Service
public class UserService {
	
	@Autowired
UserDao repo;
	
	public User savedata(User us) {
		return repo.save(us);
		
	}
	
	public User logind(UserLoginRequest login) {
		return repo.findByEmailAndPasswordAndRole(login.getEmailId(), login.getPassword(), login.getRole());
	}
	
	public User getbyemail(String email) {
		return repo.findByEmail(email);
	}
	
	
	public List<User> findall() {
		return repo.findAll();
	}
	
	public boolean grantRole(String email) {
		String role="Special";
        User user = repo.findByEmail(email);
        if (user != null) {
            user.setRole(role);
            repo.save(user);
            return true;
        }
        return false;
    }

    public boolean revokeRole(String email) {
    	String role="HR";
        User user = repo.findByEmail(email);
        if (user != null) {
            user.setRole(role);;
            repo.save(user);
            return true;
        }
        return false;
    }
    
    ///
    public List<User> findByRole(String role){
    	return repo.findByRole(role);
    }
    
    public List<User> findByPosition(String position){
    	return repo.findByPosition(position);
    }
    
    public User findByEmpNumber(String empNumber) {
    	return repo.findByEmpNumber(empNumber);
    }
    
    public User findByEmailAndRole(String email, String role) {
    	return repo.findByEmailAndRole(email, role);
    }
    
    public List<User>findallhrs(){
    	String role="HR";
    	return repo.findByRole(role);
    }
}