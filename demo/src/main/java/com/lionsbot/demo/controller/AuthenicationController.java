package com.lionsbot.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.AuthenticationRequestDTO;
import com.lionsbot.demo.dto.AuthenticationResponseDTO;
import com.lionsbot.demo.service.AuthenticationService;
import com.lionsbot.demo.service.CustomerService;

@RestController
@RequestMapping("/api/v1/")
public class AuthenicationController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
//	@PostMapping("/register")
//	public ResponseEntity<CustomerDTO> register(@RequestBody CustomerDTO customerDTO){
//		return ResponseEntity.ok(authenticationService.register(customerDTO));
//	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO AuthenticationRequestDto){
		return ResponseEntity.ok(authenticationService.authenticate(AuthenticationRequestDto));
	}
	
}
