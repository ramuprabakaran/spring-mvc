package com.sivaram.learning.mvc.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivaram.learning.mvc.dto.UserDetailsDTO;
import com.sivaram.learning.mvc.service.UserDetailService;
import com.sivaram.learning.mvc.ui.model.request.UserDetailsRequestModel;
import com.sivaram.learning.mvc.ui.model.response.UserDetailsResponseModel;

@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired
	UserDetailService userDetailService;
	
	@GetMapping
	public String getUser() {
		return "Get User was Called";
	}
	
	@PostMapping
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel requestModel) {
		
		UserDetailsResponseModel responseModel = new UserDetailsResponseModel();
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		
		BeanUtils.copyProperties(requestModel, userDetailsDTO);
		
		UserDetailsDTO createdUser = userDetailService.createUser(userDetailsDTO);
		BeanUtils.copyProperties(createdUser, responseModel);
		
		return responseModel;
	}
	
	@PutMapping
	public String updateUser() {
		return "Update User was called ";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "Delete User was called ";
	}

}
