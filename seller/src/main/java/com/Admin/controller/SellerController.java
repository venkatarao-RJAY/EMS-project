package com.Admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Admin.Dto.CommonApiResponse;
import com.Admin.Dto.RegisterUserRequestDto;
import com.Admin.Dto.UserResponseDto;
import com.Admin.resource.SellerResource;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	SellerResource resource;
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<CommonApiResponse> addseller(@RequestBody RegisterUserRequestDto dto){
		return resource.registerSeller(dto);
	}

	
	@PutMapping(value = "/update")
	public ResponseEntity<CommonApiResponse> updateSeller(@RequestBody RegisterUserRequestDto dto){
		return resource.updateSeller(dto);
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<CommonApiResponse>deleteseller(@RequestParam int id){
		return resource.deletebyid(id);
	}
	
	@GetMapping(value = "/findbyrole")
	
	public ResponseEntity<UserResponseDto>fetchseller(@RequestParam String role){
		return resource.getUsersByRole(role);
	}
}
