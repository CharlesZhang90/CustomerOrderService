package com.lionsbot.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lionsbot.demo.dto.OrderDTO;
import com.lionsbot.demo.entity.Customer;

import com.lionsbot.demo.entity.Order;
import com.lionsbot.demo.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	
	private final CustomerService customerService;
	
	@Override
	@Transactional
	public List<Order> getAll() {
		return orderRepository.findAll();			 
	}

	@Override
	@Transactional
	public List<Order> getByCustomerId(UUID customerId) {
		Customer customer = customerService.getById(customerId);
		return customer.getOrders();
	}
	
	@Override
	@Transactional
	public Order getById(UUID orderId) {
		return orderRepository.findById(orderId)
							  .orElseThrow(() -> new EntityNotFoundException("Order with id: " + orderId + " not found."));
	}
	
	//customer should not be able to update shipment, shipment should be done by admin
	@Override
	@Transactional
	public Order create(UUID customerId, OrderDTO orderDto) {
		Customer customer = customerService.getById(customerId);
		Order order = new Order();
		order.setCustomer(customer);
		order.setNumberOfItems(orderDto.getNumberOfItems());
		order.setOrderDate(LocalDate.now());
		order.setTotalPrice(orderDto.getTotalPrice());
		return orderRepository.save(order);
	}
	
	//customer should not be able to update shipment, shipment should be done by admin
	@Override
	@Transactional
	public Order update(UUID orderId, OrderDTO orderUpdateDto) {
		Order order = getById(orderId);
		order.setTotalPrice(orderUpdateDto.getTotalPrice());
		order.setNumberOfItems(orderUpdateDto.getNumberOfItems());
		return orderRepository.save(order);
	}
	
	/*
	 * This is the delete order function
	 * It is a soft delete
	 * Details please check order entity
	 * @SQLDelete is added to order entity for soft deletion
	 */
	@Override
	public void deleteById(UUID orderId) {
		Order order = getById(orderId);
		orderRepository.delete(order);	
	}

}
