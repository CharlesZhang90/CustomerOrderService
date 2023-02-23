package com.lionsbot.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lionsbot.demo.entity.Method;
import com.lionsbot.demo.repository.MethodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MethodServiceImpl implements MethodService {
	
	private final MethodRepository methodRepository;

	@Override
	@Transactional
	public Method createMethod(String methodName) {
		Method method = methodRepository.findByMethodName(methodName).orElse(new Method());
		method.setMethodName(methodName);
		return methodRepository.save(method);
	}

	@Override
	@Transactional
	public Method updateMethod(UUID methodId, String methodName) {
		Method updatedmethod = methodRepository.findById(methodId).orElse(new Method());
		updatedmethod.setMethodName(methodName);
		return methodRepository.save(updatedmethod);
	}

}
