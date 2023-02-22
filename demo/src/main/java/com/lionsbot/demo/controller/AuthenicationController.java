package com.lionsbot.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenicationController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	//allow to create an admin
	@PostMapping("/register/admins")
	public ResponseEntity<CustomerDTO> createAdmin(@RequestBody CustomerDTO customerDto) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.create(customerDto, Role.Admin)));
	}
	
	//allow to create a customer
	@PostMapping("/register/customers")
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.create(customerDto, Role.Customer)));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO AuthenticationRequestDto){
		return ResponseEntity.ok(authenticationService.authenticate(AuthenticationRequestDto));
	}
	
}
