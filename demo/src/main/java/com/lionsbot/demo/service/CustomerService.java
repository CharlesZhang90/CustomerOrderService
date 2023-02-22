package com.lionsbot.demo.service;

import java.util.List;
import java.util.UUID;

import com.lionsbot.demo.dto.CustomerDTO;
import com.lionsbot.demo.entity.Customer;
import com.lionsbot.demo.entity.Role;

public interface CustomerService {
	
	public List<Customer> getAll();
	
	public Customer getById(UUID customerId);
	
	public Customer create(CustomerDTO customerDto, Role role);
	
	public void updatePassword(UUID customerId, String password);
	
	public void deleteById(UUID customerId);
	
}
