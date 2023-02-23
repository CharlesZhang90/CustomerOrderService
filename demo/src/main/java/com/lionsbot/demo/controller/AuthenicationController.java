package com.lionsbot.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.AuthenticationRequestDTO;
import com.lionsbot.demo.dto.AuthenticationResponseDTO;
import com.lionsbot.demo.dto.CustomerDTO;
import com.lionsbot.demo.entity.Role;
import com.lionsbot.demo.service.AuthenticationService;
import com.lionsbot.demo.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenicationController {
	
	private final CustomerService customerService;
	
	private final AuthenticationService authenticationService;
	
	//allow to create an admin
	@PostMapping("/admins/register")
	public ResponseEntity<CustomerDTO> createAdmin(@Valid @RequestBody CustomerDTO customerDto) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.create(customerDto, Role.Admin)));
	}
	
	//allow to create a customer
	@PostMapping("/customers/register")
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.create(customerDto, Role.Customer)));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO AuthenticationRequestDto){
		return ResponseEntity.ok(authenticationService.authenticate(AuthenticationRequestDto));
	}
	
}
