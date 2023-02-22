package com.lionsbot.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lionsbot.demo.entity.Method;

public interface MethodRepository extends JpaRepository<Method, UUID> {
	
	public Optional<Method> findByMethodName(String methodName);

}
