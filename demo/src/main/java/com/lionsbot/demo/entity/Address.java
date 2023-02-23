package com.lionsbot.demo.entity;

import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.lionsbot.demo.dto.CustomerDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="addresses")
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE address_id=?")
@Where(clause = "deleted=false")
public class Address {

	@Id
	@GeneratedValue
	@Column(name="address_id")
	private UUID addressId;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="postal_code")
	private int postalCode;
	
	@Column(name="country")
	private String country;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;
	
	public Address(CustomerDTO customerDto) {
		this.address1 = customerDto.getAddress1();
		this.address2 = customerDto.getAddress2();
		this.postalCode = Integer.parseInt(customerDto.getPostalCode());
		this.country = customerDto.getCountry().toUpperCase();
	}
	
}

