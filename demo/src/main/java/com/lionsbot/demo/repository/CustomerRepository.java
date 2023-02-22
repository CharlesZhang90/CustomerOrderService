package com.lionsbot.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lionsbot.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
	
	Optional<Customer> findByCustomerName(String customerName);
	
}
