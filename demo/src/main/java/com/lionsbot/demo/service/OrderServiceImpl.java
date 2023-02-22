package com.lionsbot.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lionsbot.demo.dto.OrderDTO;
import com.lionsbot.demo.entity.Customer;
import com.lionsbot.demo.entity.Method;
import com.lionsbot.demo.entity.Order;
import com.lionsbot.demo.entity.Shipment;
import com.lionsbot.demo.repository.CustomerRepository;
import com.lionsbot.demo.repository.MethodRepository;
import com.lionsbot.demo.repository.OrderRepository;
import com.lionsbot.demo.repository.ShipmentRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${estimated.shipping.date}")
	private int shippingDate;
	
	@Value("${default.shipping.method}")
	private String defaultShippingMethod;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
		Order savedOrder = orderRepository.save(order);
		List<Order> orders = customer.getOrders();
		orders.add(savedOrder);
		customer.setOrders(orders);
		customerRepository.save(customer);
		return savedOrder;
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

	@Override
	public void deleteById(UUID orderId) {
		Order order = getById(orderId);
		orderRepository.delete(order);	
	}

}
