package com.sivaram.learning.mvc.repo;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sivaram.learning.mvc.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByUserId(String userId);
	
}
