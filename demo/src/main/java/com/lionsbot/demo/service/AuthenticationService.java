package com.lionsbot.demo.service;

import com.lionsbot.demo.dto.AuthenticationRequestDTO;
import com.lionsbot.demo.dto.AuthenticationResponseDTO;

public interface AuthenticationService {
	
//	public CustomerDTO register(CustomerDTO customerDto);

	public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDto);
	
}
