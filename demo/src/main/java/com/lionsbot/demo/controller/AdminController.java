package com.lionsbot.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.dto.CustomerDTO;
import com.lionsbot.demo.dto.OrderDTO;
import com.lionsbot.demo.entity.Customer;
import com.lionsbot.demo.entity.Order;
import com.lionsbot.demo.entity.Role;
import com.lionsbot.demo.service.CustomerService;
import com.lionsbot.demo.service.MethodService;
import com.lionsbot.demo.service.OrderService;
import com.lionsbot.demo.service.ShipmentService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MethodService methodService;
	
	@Autowired
	private ShipmentService shipmentService;
	
	//allow admin get all customers
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
		List<CustomerDTO> customerDtos = customerService.getAll()
					  								    .stream()
					                                    .map(CustomerDTO::new)
					                                    .collect(Collectors.toList());
		return ResponseEntity.ok().body(customerDtos);
	}
	
	// allow admin get all orders
	@GetMapping("/orders")
	public ResponseEntity<List<OrderDTO>> findAllOrders(){
		List<OrderDTO> OrderDtos = orderService.getAll()
					   						   .stream()
					   						   .map(OrderDTO::new)
					   						   .collect(Collectors.toList());
		return ResponseEntity.ok().body(OrderDtos);
	}
	
	// allow admin to delete a customer
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable UUID customerId) {
		customerService.deleteById(customerId);
		return ResponseEntity.ok().body("Customer successfully deleted.");
	}
	
	// allow admin to delete an order
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId) {
		orderService.deleteById(orderId);
		return ResponseEntity.ok().body("Order successfully deleted.");
	}
	
	//allow admin to create an method
	@PostMapping("/methods")
	public ResponseEntity<String> createMethod(@RequestBody String methodName) {
		methodService.createMethod(methodName);
		return ResponseEntity.ok().body("Method created.");
	}
	
	//allow admin to create an shipment
	@PostMapping("/shipments/{orderId}")
	public ResponseEntity<String> createShipment(@PathVariable UUID orderId, @RequestParam("shipmentDate") LocalDate shipmentDate, @RequestParam("methodName") String methodName) {
		shipmentService.createShipment(orderId, shipmentDate, methodName);
		return ResponseEntity.ok().body("Shipment created.");
	}
}
