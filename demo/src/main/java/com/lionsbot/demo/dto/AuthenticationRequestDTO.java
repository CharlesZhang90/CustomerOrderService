package com.lionsbot.demo.dto;

import com.lionsbot.demo.validation.Password;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {
	
	@NotNull
	private String userName;
	
	@Password
	private String password;
	
}
