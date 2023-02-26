package com.lionsbot.demo.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.lionsbot.demo.validation.Password;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {
		
	private final CustomerService customerService;
	
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
	
	//allow admin get all customers
	@GetMapping("/customers")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
		return ResponseEntity.ok().body(customerService.getAll()
				    								   .stream()
                                                       .map(CustomerDTO::new)
                                                       .collect(Collectors.toList()));
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable UUID customerId) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.getById(customerId)));
	}
	
	@PutMapping("/customers/changepassword/{customerId}")
	public ResponseEntity<String> updatePassword(@PathVariable UUID customerId, @RequestBody @Password String newPassword) {
		customerService.updatePassword(customerId, newPassword);
		return ResponseEntity.ok().body("Password Updated.");
	}
	
	// allow admin to delete a customer
	@DeleteMapping("/customers/{customerId}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> deleteCustomer(@PathVariable UUID customerId) {
		customerService.deleteById(customerId);
		return ResponseEntity.ok().body("Customer successfully deleted.");
	}
	
}
