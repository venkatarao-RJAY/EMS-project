package com.example.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dto.CommonApiResponse;
import com.example.dto.UserResponseDto;
import com.example.entity.LeaveBalance;
import com.example.entity.User;
import com.example.service.LeaveBalanceService;
import com.example.service.UserService;

@Service
public class LeaveBalanceResource {

	@Autowired
	LeaveBalanceService service;
	
	@Autowired
	UserService userservice;
	
	 public ResponseEntity<CommonApiResponse> addleave(List<LeaveBalance> leaveList) {
	        CommonApiResponse response = new CommonApiResponse();

	        User byEmpNumber = userservice.findByEmpNumber(leaveList.get(0).getEmpnumber());

	        if (byEmpNumber == null) {
	            response.setMessage("Employee not found");
	            response.setStatus(false);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        for (LeaveBalance leave : leaveList) {
	            leave.setUser(byEmpNumber);
	            leave.setEmployeeName(byEmpNumber.getName());
	            leave.setEmpnumber(byEmpNumber.getEmpNumber());// Set the user reference
	            service.addleave(leave);     // Save each leave balance
	        }

	        response.setMessage("Leave balances added successfully");
	        response.setStatus(true);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	
	 public ResponseEntity<UserResponseDto>checkavailabeleaves(String empnumber){
		 UserResponseDto response = new UserResponseDto();
		 
		 List<LeaveBalance> byEmpnumber = service.findByEmpnumber(empnumber);
		 
		 if(byEmpnumber.isEmpty()) {
			 response.setMessage("No data found or wrong emp number");
		        response.setStatus(false);
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 response.setLeaveBalance(byEmpnumber);
		 response.setMessage("data found");
	        response.setStatus(true);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
}