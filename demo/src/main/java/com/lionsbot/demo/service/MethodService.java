package com.lionsbot.demo.service;

import java.util.UUID;

import com.lionsbot.demo.entity.Method;

public interface MethodService {
	
	public Method createMethod(String methodName);
	
	public Method updateMethod(UUID method, String methodName);
	
}
