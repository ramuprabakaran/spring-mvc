package com.sivaram.learning.mvc.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sivaram.learning.mvc.dto.UserDetailsDTO;
import com.sivaram.learning.mvc.entity.UserEntity;

@Mapper
public interface UserDetailsMapper {

	@Mapping(target = "password", ignore = true)
	UserDetailsDTO entityToDTO(UserEntity entity);
	
	UserEntity dtotoEntity(UserDetailsDTO detailsDTO);
	
	List<UserDetailsDTO> entityToDTOList(List<UserEntity> userEntities);
	List<UserEntity> dtotoEntityList(List<UserDetailsDTO> detailsDTOs);
}
