package com.lionsbot.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.service.MethodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MethodController {
	
	private final MethodService methodService;
	
	//allow admin to create an method
	@PostMapping("/methods")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> createMethod(@RequestBody String methodName) {
		methodService.createMethod(methodName);
		return ResponseEntity.ok().body("Method created.");
	}
	
}
