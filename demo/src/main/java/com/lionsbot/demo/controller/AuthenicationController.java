package com.lionsbot.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.AuthenticationRequestDTO;
import com.lionsbot.demo.dto.AuthenticationResponseDTO;
import com.lionsbot.demo.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenicationController {
		
	private final AuthenticationService authenticationService;
		
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO AuthenticationRequestDto){
		return ResponseEntity.ok(authenticationService.authenticate(AuthenticationRequestDto));
	}
	
}
