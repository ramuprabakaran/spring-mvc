package com.sivaram.learning.mvc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sivaram.learning.mvc.dto.UserDetailsDTO;
import com.sivaram.learning.mvc.entity.UserEntity;
import com.sivaram.learning.mvc.mapper.UserDetailsMapper;
import com.sivaram.learning.mvc.repo.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserDetailsMapper userMapper;

	@Override
	public UserDetailsDTO createUser(UserDetailsDTO userDetailsDTO) {

		Optional<UserEntity> findByEmail = userRepository.findByEmail(userDetailsDTO.getEmail());

//		findByEmail.orElseThrow(RuntimeException::new);
		findByEmail.ifPresent(user -> {
			throw new RuntimeException("email Id : " + user.getEmail() + " is already Exists");
		});

		UserEntity entity = new UserEntity();
		UserDetailsDTO dto = new UserDetailsDTO();

		//BeanUtils.copyProperties(userDetailsDTO, entity);
		entity = userMapper.dtotoEntity(userDetailsDTO);
		

		entity.setEncryptedPassword("123456");
		entity.setUserId("111");

		UserEntity userEntity = userRepository.save(entity);
		//BeanUtils.copyProperties(userEntity, dto);
		dto = userMapper.entityToDTO(userEntity);

		return dto;
	}

	@Override
	public UserDetailsDTO getUserByUserId(String userId) {
		System.out.println("Supplied User Id" + userId);
		Optional<UserEntity> userByUserId = userRepository.findByUserId(userId);
		
		return userByUserId.map(userMapper::entityToDTO).orElseThrow(() -> new RuntimeException("User Id : " + userId + " is not Exists"));
		
//		userByUserId.orElseThrow(() -> new RuntimeException("User Id : " + userByUserId.get().getUserId() + " is not Exists"));
//		
//		if(userByUserId.isPresent()) {
//			return userMapper.entityToDTO(userByUserId.get());
//		}
//		else {
//			throw new RuntimeException("User Id : " + userByUserId.get().getUserId() + " is not Exists");
//		}
//		
		
	}

}
