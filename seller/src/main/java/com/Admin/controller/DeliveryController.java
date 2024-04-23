package com.Admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Admin.Dto.CommonApiResponse;
import com.Admin.Dto.RegisterUserRequestDto;
import com.Admin.Dto.UserResponseDto;
import com.Admin.resource.DeliveryResource;

@RestController
@RequestMapping("/deliveryperson")
public class DeliveryController {
	
	@Autowired
	DeliveryResource resource;
	
	@PostMapping(value = "/add")
	public ResponseEntity<CommonApiResponse> addDeliveryPerson(@RequestBody RegisterUserRequestDto dto){
	return	resource.addDeliveryper(dto);
	}

	
	@GetMapping(value = "/fectbySellerid")
	public ResponseEntity<UserResponseDto> findbySellerid(@RequestParam int sellerid){
		return resource.findDeliveryBySellerId(sellerid);
	}
	
}
