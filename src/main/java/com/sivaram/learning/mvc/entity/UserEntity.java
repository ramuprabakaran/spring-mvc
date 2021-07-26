package com.sivaram.learning.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PUBLIC_USERS")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 5858823140660442778L;

	@Id
	@GeneratedValue
	private Long ID;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(length = 50, nullable = false)
	private String firstName;
	
	@Column(length = 50, nullable = false)
	private String lastName;
	
	@Column(length = 120, nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Column(nullable = false)
	@Builder.Default
	private boolean emailVerificationStatus = false;
	
	private String emailVerificationToken;

	
}
