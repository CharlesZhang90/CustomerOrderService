package com.lionsbot.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.CustomerDTO;
import com.lionsbot.demo.entity.Role;
import com.lionsbot.demo.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable UUID customerId) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.getById(customerId)));
	}
	
	@PutMapping("/changepassword/{customerId}")
	public ResponseEntity<String> updatePassword(@PathVariable UUID customerId, @RequestBody String newPassword) {
		customerService.updatePassword(customerId, newPassword);
		return ResponseEntity.ok().body("Password Updated.");
	}
	
}
