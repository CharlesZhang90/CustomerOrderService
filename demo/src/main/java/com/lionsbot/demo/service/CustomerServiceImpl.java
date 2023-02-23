package com.lionsbot.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	private final PasswordEncoder passwordEncoder;

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
		Customer customer = new Customer(customerDto, role);
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		Address address = new Address(customerDto);
		address.setCustomer(customer);
		customer.setAddress(address);
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void updatePassword(UUID customerId, String password) {
		customerRepository.save(Optional.of(getById(customerId))
										.map(c -> {c.setPassword(passwordEncoder.encode(password));
												   return c;})
										.get());
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
		customerRepository.delete(getById(customerId));
	}

}
