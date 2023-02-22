package com.lionsbot.demo.service;

import java.util.List;
import java.util.UUID;

import com.lionsbot.demo.dto.OrderDTO;
import com.lionsbot.demo.entity.Order;

public interface OrderService {
	
	public List<Order> getAll();
	
	public List<Order> getByCustomerId(UUID customerId);
	
	public Order getById(UUID orderId);
	
	public Order create(UUID customerId, OrderDTO orderDto);
	
	public Order update(UUID orderId, OrderDTO orderUpdateDto);
	
	public void deleteById(UUID orderId);
}