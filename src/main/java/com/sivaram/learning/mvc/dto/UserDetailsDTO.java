package com.sivaram.learning.mvc.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDTO implements Serializable{

	private static final long serialVersionUID = 5081922812669195432L;
	
	private Long ID;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	@Builder.Default
	private boolean emailVerificationStatus = false;
	private String emailVerificationToken;
}
