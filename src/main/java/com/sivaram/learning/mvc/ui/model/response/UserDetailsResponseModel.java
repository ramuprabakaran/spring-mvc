package com.sivaram.learning.mvc.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsResponseModel {

	private Long ID;
	private String firstName;
	private String lastName;
	private String email;
}
