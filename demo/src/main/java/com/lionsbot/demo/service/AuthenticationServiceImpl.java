package com.lionsbot.demo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lionsbot.demo.config.JwtService;
import com.lionsbot.demo.dto.AuthenticationRequestDTO;
import com.lionsbot.demo.dto.AuthenticationResponseDTO;
import com.lionsbot.demo.entity.Customer;
import com.lionsbot.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final CustomerRepository customerRepository;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUserName(), authenticationRequestDto.getPassword())
				);
		Customer customer = customerRepository.findByCustomerName(authenticationRequestDto.getUserName()).orElseThrow();
		String jwtToken = jwtService.generateToken(customer);
		return AuthenticationResponseDTO.builder()
				.token(jwtToken)
				.build();
	}

}
