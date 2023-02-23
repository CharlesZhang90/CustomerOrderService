package com.lionsbot.demo.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.lionsbot.demo.entity.Order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private UUID orderId;
	
	private LocalDate orderDate;
	
	@NotNull(message="Total price should not be null.")
	private double totalPrice;
	
	@NotNull(message="Number of items should not be null.")
	private int numberOfItems;
	
	private List<Map<LocalDate, String>> shipments;
		
	public OrderDTO(Order order) {
		this.orderId = order.getOrderId();
		this.orderDate = order.getOrderDate();
		this.totalPrice = order.getTotalPrice();
		this.numberOfItems = order.getNumberOfItems();
		if(order.getShipments() != null) {
			this.shipments = order.getShipments().stream()
					 .map(shipment -> {
						 Map<LocalDate, String> m = new HashMap<>();
						 m.put(shipment.getShipmentDate(), shipment.getMethod().getMethodName());
						 return m;})
					 .collect(Collectors.toList());
		}
	}
	
}
