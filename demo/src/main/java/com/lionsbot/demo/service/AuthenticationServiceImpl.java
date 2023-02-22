package com.lionsbot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lionsbot.demo.config.JwtService;
import com.lionsbot.demo.dto.AuthenticationRequestDTO;
import com.lionsbot.demo.dto.AuthenticationResponseDTO;
import com.lionsbot.demo.repository.CustomerRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@Override
//	public CustomerDTO register(CustomerDTO customerDto) {
//		Customer customer = Customer.builder()
//						   .customerName(customerDto.getCustomerName())
//						   .email(customerDto.getEmail())
//						   .password(passwordEncoder.encode(customerDto.getPassword()))
//						   .contactNo(customerDto.getContactNo())
//						   .role(Role.Admin)
//						   .build();
//		Address address = new Address();
//		address.setAddress1(customerDto.getAddress1());
//		address.setAddress2(customerDto.getAddress2());
//		address.setPostalCode(customerDto.getPostalCode());
//		address.setCountry(customerDto.getCountry().toUpperCase());
//		address.setCustomer(customer);
//		customer.setAddress(address);
//		return new CustomerDTO(customerRepository.save(customer));
//	}

	@Override
	public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUserName(), authenticationRequestDto.getPassword())
				);
		var customer = customerRepository.findByCustomerName(authenticationRequestDto.getUserName()).orElseThrow();
		var jwtToken = jwtService.generateToken(customer);
		return AuthenticationResponseDTO.builder()
				.token(jwtToken)
				.build();
	}

}
