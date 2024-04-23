package com.Admin.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Admin.Dto.CommonApiResponse;
import com.Admin.Dto.RegisterUserRequestDto;
import com.Admin.Dto.UserDto;
import com.Admin.Dto.UserResponseDto;
import com.Admin.entity.User;
import com.Admin.service.DeliveryService;
import com.Admin.service.UserService;

@Service
public class DeliveryResource {
	
	@Autowired
	DeliveryService ds;
	
	@Autowired
	 UserService us;
	
	
	public ResponseEntity<CommonApiResponse> addDeliveryper(RegisterUserRequestDto dto){
		
		
		CommonApiResponse api = new CommonApiResponse();
		
		if(dto==null) {
			api.setResponseMessage("missing input");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.BAD_REQUEST);
		}
		
		if(dto.getEmailId()==null) {
			api.setResponseMessage("Email is missing");
			api.setSuccess(false);
			 return new ResponseEntity<CommonApiResponse>(api,HttpStatus.BAD_REQUEST);
		}
		
		User userEntity = RegisterUserRequestDto.toUserEntity(dto);
		
		userEntity.setRole("DeliveryPerson");
		userEntity.setStatus("Active");
		
		User add = ds.adddeliverper(userEntity);
		
		if(add==null) {
			api.setResponseMessage("failed to register delivery person");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.INTERNAL_SERVER_ERROR);
		}else
			
			api.setResponseMessage("delivery person register success");
		api.setSuccess(true);
		return new ResponseEntity<CommonApiResponse>(api,HttpStatus.OK);
		
	}
	
	public ResponseEntity<UserResponseDto> findDeliveryBySellerId(int sellerId) {
	    UserResponseDto api = new UserResponseDto();

	    if (sellerId <= 0) {
	        api.setResponseMessage("Please check the seller ID");
	        api.setSuccess(false);
	        return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
	    }

	    User seller = ds.findbyid(sellerId); // Assuming ds.findbyid is replaced with findById
	    if (seller == null) {
	        api.setResponseMessage("Seller not found");
	        api.setSuccess(false);
	        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND); // Correcting status to NOT_FOUND
	    }

	    List<User> deliveryPersons = ds.findbysellerroleAndStatus(seller, "DeliveryPerson", "Active");
	    if (deliveryPersons.isEmpty()) {
	        api.setResponseMessage("No delivery persons found");
	        api.setSuccess(false);
	        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND); // Correcting status to NOT_FOUND
	    }

	    List<UserDto> users = new ArrayList<>();
	    for (User deliveryPerson : deliveryPersons) {
	        UserDto userDto = UserDto.toUserDtoEntity(deliveryPerson);
	        users.add(userDto);
	    }
	    api.setUsers(users);
	    api.setResponseMessage("Data fetched successfully");
	    api.setSuccess(true);
	    return new ResponseEntity<>(api, HttpStatus.OK);
	}

}
