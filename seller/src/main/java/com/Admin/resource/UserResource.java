package com.Admin.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Admin.Dto.CommonApiResponse;
import com.Admin.Dto.RegisterUserRequestDto;
import com.Admin.entity.User;
import com.Admin.service.UserService;

@Service
public class UserResource {
	@Autowired
	UserService us;

	
	public ResponseEntity<CommonApiResponse> registeradmin(RegisterUserRequestDto dto) {
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

		u.setRole("Admin");
		u.setStatus("Active");
		User user = us.add(u);
		if (user== null) {
			api.setResponseMessage("failed to register to login");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api, HttpStatus.BAD_REQUEST);
		} else
		api.setResponseMessage("register success");
		api.setSuccess(true);
		return new ResponseEntity<CommonApiResponse>(api, HttpStatus.OK);
	}
	
}
