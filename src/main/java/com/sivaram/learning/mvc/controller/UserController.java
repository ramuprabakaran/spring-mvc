package com.sivaram.learning.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivaram.learning.mvc.dto.UserDetailsDTO;
import com.sivaram.learning.mvc.service.UserDetailService;
import com.sivaram.learning.mvc.ui.model.request.UserDetailsRequestModel;
import com.sivaram.learning.mvc.ui.model.response.OperationalStatusModel;
import com.sivaram.learning.mvc.ui.model.response.UserDetailsResponseModel;

@RestController
@RequestMapping(value = "users")
public class UserController {
	
	@Autowired
	UserDetailService userDetailService;
	
	
	@GetMapping(path = "/{userId}")
	public UserDetailsResponseModel getUser(@PathVariable String userId) {
		
		UserDetailsResponseModel responseModel = new UserDetailsResponseModel();
		
		UserDetailsDTO userByUserId = userDetailService.getUserByUserId(userId);
		
		BeanUtils.copyProperties(userByUserId, responseModel);
		
		return responseModel;
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
	
	@PutMapping(path = "/{userId}")
	public UserDetailsResponseModel updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel requestModel) {
		UserDetailsResponseModel responseModel = new UserDetailsResponseModel();
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		
		BeanUtils.copyProperties(requestModel, userDetailsDTO);
		
		UserDetailsDTO updatedUser = userDetailService.updateUser(userId, userDetailsDTO);
		BeanUtils.copyProperties(updatedUser, responseModel);
		
		return responseModel;
	}
	
	@DeleteMapping(path = "/{userId}")
	public OperationalStatusModel deleteUser(@PathVariable String userId) {
		
		userDetailService.deleteUser(userId);
		
		return OperationalStatusModel.builder()
				.operationName("DELETE")
				.operationResult("SUCESS")
				.build();
	}
	
	@GetMapping
	public List<UserDetailsResponseModel> getUsers(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "25") int limit ){
		
		List<UserDetailsResponseModel> userList = new ArrayList<>();
		
		
		List<UserDetailsDTO> users = userDetailService.getUsers(page, limit);
		
		users.stream().forEach((user) -> {
			UserDetailsResponseModel model = new UserDetailsResponseModel();
			BeanUtils.copyProperties(user, model);
			userList.add(model);
		});
		
		return userList;
	}

}
