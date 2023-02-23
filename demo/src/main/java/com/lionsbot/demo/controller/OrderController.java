package com.lionsbot.demo.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.OrderDTO;
import com.lionsbot.demo.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	// allow admin get all orders
	@GetMapping
	@PreAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<List<OrderDTO>> findAllOrders(){
		return ResponseEntity.ok().body(orderService.getAll()
				   									.stream()
				   									.map(OrderDTO::new)
				   									.collect(Collectors.toList()));
	}
	
	//Fetch all orders to a specific customer by customer id
	@GetMapping("/{customerId}")
	public ResponseEntity<List<OrderDTO>> findAllOrdersByCustomerId(@PathVariable UUID customerId){
    	return ResponseEntity.ok(orderService.getByCustomerId(customerId)
				   							 .stream()
				   							 .map(OrderDTO::new)
				   							 .collect(Collectors.toList()));
	}
		
	// Create an order based on customer id
	@PostMapping("/{customerId}")
	public ResponseEntity<OrderDTO> createOrder(@PathVariable UUID customerId, @Valid @RequestBody OrderDTO orderDto) {
    	return ResponseEntity.ok(new OrderDTO(orderService.create(customerId, orderDto)));
	}
	
	// Update an order based on order id
	@PutMapping("/{orderId}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable UUID orderId, @Valid @RequestBody OrderDTO orderDto) {
    	return ResponseEntity.ok(new OrderDTO(orderService.update(orderId, orderDto)));
	}
	
	// allow admin to delete an order
	@DeleteMapping("/{orderId}")
	@PreAuthorize("hasAnyRole('Admin')")
	public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId) {
		orderService.deleteById(orderId);
		return ResponseEntity.ok().body("Order successfully deleted.");
	}
}