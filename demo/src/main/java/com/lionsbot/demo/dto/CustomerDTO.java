package com.lionsbot.demo.dto;

import java.util.UUID;

import com.lionsbot.demo.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private UUID customerId;
	
	private String customerName;
	
	private String email;
	
	private String password;
	
	private String contactNo;
	
	private String address1;
	
	private String address2;
	
	private String postalCode;
	
	private String country;
	
	public CustomerDTO(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.customerName = customer.getCustomerName();
		this.email = customer.getEmail();
		this.contactNo = customer.getContactNo();
		this.address1 = customer.getAddress().getAddress1();
		this.address2 = customer.getAddress().getAddress2();
		this.postalCode = String.valueOf(customer.getAddress().getPostalCode());
		this.country = customer.getAddress().getCountry();
	}
	
}
