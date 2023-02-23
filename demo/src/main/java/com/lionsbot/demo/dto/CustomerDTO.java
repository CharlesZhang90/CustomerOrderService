package com.lionsbot.demo.dto;

import java.util.UUID;

import com.lionsbot.demo.entity.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	@NotNull(message="Customer name should not be null.")
	@Size(min=2, max=20, message="Invalid Customer name.")
	private String customerName;
	
	@Email(message="Invalid email.")
	@NotNull(message="Email should not be null.")
	private String email;
	
	private String password;
	
	@NotNull(message="Contact number should not be null.")
	@Size(min = 8, max = 8, message = "Contact Number should be within 8 digits.")
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
