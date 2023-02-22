package com.lionsbot.demo.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lionsbot.demo.dto.CustomerDTO;
import com.lionsbot.demo.entity.Address;
import com.lionsbot.demo.entity.Customer;
import com.lionsbot.demo.entity.Role;
import com.lionsbot.demo.exception.EntityAlreadyExistException;
import com.lionsbot.demo.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(UUID customerId) {
		return customerRepository.findById(customerId)
				  .orElseThrow(() -> new EntityNotFoundException("Customer with id: " + customerId + " not found."));
	}
	
	@Override
	@Transactional
	public Customer create(CustomerDTO customerDto, Role role) {
		//check if customer existence base on email
		customerRepository.findByCustomerName(customerDto.getCustomerName())
						  .ifPresent((customer)-> {throw new EntityAlreadyExistException("Customer already Exists.");});
		Customer customer = new Customer();
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		customer.setContactNo(customerDto.getContactNo());
		customer.setRole(role);
		Address address = new Address();
		address.setAddress1(customerDto.getAddress1());
		address.setAddress2(customerDto.getAddress2());
		address.setPostalCode(Integer.parseInt(customerDto.getPostalCode()));
		address.setCountry(customerDto.getCountry().toUpperCase());
		address.setCustomer(customer);
		customer.setAddress(address);
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void updatePassword(UUID customerId, String password) {
		Customer customer = getById(customerId);
		customer.setPassword(passwordEncoder.encode(password));
		customerRepository.save(customer);
	}
	/*
	 * This is the delete customer function
	 * It is a soft delete
	 * Details please check customer entity
	 * @SQLDelete is added to customer entity for soft deletion
	 */
	@Override
	@Transactional
	public void deleteById(UUID customerId) {
		Customer customer = getById(customerId);
		customerRepository.delete(customer);
	}

}
