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
import com.Admin.service.SellerService;

@Service
public class SellerResource {
	@Autowired
	SellerService ss;
	

	public ResponseEntity<CommonApiResponse> registerSeller(RegisterUserRequestDto dto) {
		CommonApiResponse api = new CommonApiResponse();
		if (dto == null) {
			api.setResponseMessage("user is null");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api, HttpStatus.BAD_REQUEST);
		}
		if (dto.getEmailId() == null) {
			api.setResponseMessage("missing input");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api, HttpStatus.BAD_REQUEST);
		}
		User u = RegisterUserRequestDto.toUserEntity(dto);

		u.setRole("Seller");
		u.setStatus("Active");
		User user = ss.addSeller(u);
		if (user== null) {
			api.setResponseMessage("failed to register to login");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api, HttpStatus.BAD_REQUEST);
		} else
		api.setResponseMessage("register success");
		api.setSuccess(true);
		return new ResponseEntity<CommonApiResponse>(api, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> updateSeller(RegisterUserRequestDto dto){
		CommonApiResponse api = new CommonApiResponse();
		
		if(dto==null) {
			api.setResponseMessage("missing input");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.BAD_REQUEST);
		}
		 List<User> user = ss.findbyemailid(dto.getEmailId());
		 User userEntity = RegisterUserRequestDto.toUserEntity(dto);
		 User update = ss.update((User) userEntity);
		 if(update==null) {
			 api.setResponseMessage("data not found");
			 api.setSuccess(false);
			 return new ResponseEntity<CommonApiResponse>(api,HttpStatus.INTERNAL_SERVER_ERROR);
		 }else
			 api.setResponseMessage("update data successfull");
		 api.setSuccess(true);
		 return new ResponseEntity<CommonApiResponse>(api,HttpStatus.OK);
			
		
	}
	
	
public ResponseEntity<CommonApiResponse>deletebyid(int id){
		
		CommonApiResponse api = new CommonApiResponse();
		if(id==0) {
			api.setResponseMessage("id is null");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.BAD_REQUEST);
		}
		boolean b=ss.deletebyid(id);
		if(b) {
			api.setResponseMessage(id+" id delted successfully");
			api.setSuccess(true);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.OK);
		}else
			api.setResponseMessage("id not found");
		api.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(api,HttpStatus.INTERNAL_SERVER_ERROR);
	}


public ResponseEntity<UserResponseDto> getUsersByRole(String role) {
    UserResponseDto response = new UserResponseDto();

    // Check if role is null or empty
    if (role == null || role.isEmpty()) {
        response.setResponseMessage("Missing role");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    List<User> users = ss.fetchall(role);

    if (users.isEmpty()) {
        response.setResponseMessage("No Users Found");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    List<UserDto> userDtos = new ArrayList<>();

    for (User user : users) {
        UserDto dto = UserDto.toUserDtoEntity(user);

        

        userDtos.add(dto);
    }

    response.setUsers(userDtos);
    response.setResponseMessage("Users Fetched Successfully");
    response.setSuccess(true);

    return new ResponseEntity<>(response, HttpStatus.OK);
}

}

