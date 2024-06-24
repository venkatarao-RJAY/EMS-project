package com.example.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.AttendanceDTO;

@FeignClient("Attendance1")
public interface AttedanceFeign {

	 @PostMapping("/attendance/login")
	    public AttendanceDTO login(@RequestParam Long userId);
	 
	 @PostMapping("/attendance/logout")
	    public AttendanceDTO logout(@RequestParam Long userId);
	
}
