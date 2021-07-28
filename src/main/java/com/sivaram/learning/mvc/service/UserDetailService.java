package com.sivaram.learning.mvc.service;

import java.util.List;

import com.sivaram.learning.mvc.dto.UserDetailsDTO;

public interface UserDetailService {

	UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO);
	
	UserDetailsDTO getUserByUserId(String userId);

	UserDetailsDTO updateUser(String userId, UserDetailsDTO userDetailsDTO);
	
	void deleteUser(String userId);

	List<UserDetailsDTO> getUsers(int page, int limit);
}
