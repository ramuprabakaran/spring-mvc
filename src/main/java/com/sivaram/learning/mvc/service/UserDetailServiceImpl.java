package com.sivaram.learning.mvc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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



	@Override
	public UserDetailsDTO updateUser(String userId, UserDetailsDTO userDetailsDTO) {
		
		Optional<UserEntity> userByUserId = userRepository.findByUserId(userId);
		
//		userByUserId.ifPresent((user) -> {
//			user.setFirstName(userDetailsDTO.getFirstName());
//		});
//		
//		userByUserId.orElseThrow(() -> new RuntimeException("User Id : " + userId + " is not Exists"));
		
//		userByUserId.ifPresentOrElse((user) -> {
//												user.setFirstName(userDetailsDTO.getFirstName());
//											},
//											() ->  new RuntimeException("User Id : " + userId + " is not Exists"));
		
		if(userByUserId.isPresent()) {
			userByUserId.get().setFirstName(userDetailsDTO.getFirstName());	
		}
		else {
			throw new RuntimeException("User Id : " + userId + " is not Exists");
		}
		
		UserEntity updatedUser = userRepository.save(userByUserId.get());
		
		
		return userMapper.entityToDTO(updatedUser);
	}

	@Override
	public void deleteUser(String userId) {
		Optional<UserEntity> userByUserId = userRepository.findByUserId(userId);
		
		userByUserId.orElseThrow(() -> new RuntimeException("User Id : " + userId + " is not Exists"));
		
		userRepository.delete(userByUserId.get());
		
	}

	@Override
	public List<UserDetailsDTO> getUsers(int page, int limit) {

		Pageable pageable = PageRequest.of(page, limit);
		
		Page<UserEntity> allUsersPage = userRepository.findAll(pageable);		
		List<UserEntity> allUsers = allUsersPage.getContent();
		
		allUsers.stream().forEach(userMapper::entityToDTO);
		
		List<UserDetailsDTO> entityToDTOList = userMapper.entityToDTOList(allUsers);
		
		return entityToDTOList;
	}

}
