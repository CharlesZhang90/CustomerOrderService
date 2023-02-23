package com.lionsbot.demo.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.CustomerDTO;
import com.lionsbot.demo.service.CustomerService;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//allow admin get all customers
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
		return ResponseEntity.ok().body(customerService.getAll()
				    								   .stream()
                                                       .map(CustomerDTO::new)
                                                       .collect(Collectors.toList()));
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable UUID customerId) {
		return ResponseEntity.ok().body(new CustomerDTO(customerService.getById(customerId)));
	}
	
	@PutMapping("/changepassword/{customerId}")
	public ResponseEntity<String> updatePassword(@PathVariable UUID customerId, @RequestBody String newPassword) {
		customerService.updatePassword(customerId, newPassword);
		return ResponseEntity.ok().body("Password Updated.");
	}
	
	// allow admin to delete a customer
	@DeleteMapping("/{customerId}")
	@PreAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<String> deleteCustomer(@PathVariable UUID customerId) {
		customerService.deleteById(customerId);
		return ResponseEntity.ok().body("Customer successfully deleted.");
	}
	
}
