package com.sivaram.learning.mvc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sivaram.learning.mvc.dto.UserDetailsDTO;
import com.sivaram.learning.mvc.entity.UserEntity;
import com.sivaram.learning.mvc.repo.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO) {

		Optional<UserEntity> findByEmail = userRepository.findByEmail(userDetailsDTO.getEmail());

//		findByEmail.orElseThrow(RuntimeException::new);
		findByEmail.ifPresent(user -> {
			throw new RuntimeException("email Id : " + user.getEmail() + " is already Exists");
		});

		UserEntity entity = new UserEntity();
		UserDetailsDTO dto = new UserDetailsDTO();

		BeanUtils.copyProperties(userDetailsDTO, entity);

		entity.setEncryptedPassword("123456");
		entity.setUserId("Test User");

		UserEntity userEntity = userRepository.save(entity);
		BeanUtils.copyProperties(userEntity, dto);

		return dto;
	}

}
