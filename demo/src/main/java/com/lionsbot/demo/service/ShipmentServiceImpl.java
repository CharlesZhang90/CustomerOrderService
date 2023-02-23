package com.lionsbot.demo.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lionsbot.demo.entity.Method;
import com.lionsbot.demo.entity.Order;
import com.lionsbot.demo.entity.Shipment;
import com.lionsbot.demo.repository.MethodRepository;
import com.lionsbot.demo.repository.OrderRepository;
import com.lionsbot.demo.repository.ShipmentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
	
	private final MethodRepository methodRepository;
	
	private final ShipmentRepository shipmentRepository;
	
	private final OrderRepository orderRepository;
	
	@Override
	@Transactional
	public Shipment createShipment(UUID orderId, LocalDate shipmentDate, String methodName) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order with id: " + orderId + " not found."));
		Method method = methodRepository.findByMethodName(methodName).orElseThrow(() -> new EntityNotFoundException("Method not found."));
		Shipment shipment = new Shipment();
		shipment.setShipmentDate(shipmentDate);
		shipment.setMethod(method);
		shipment.setOrder(order);
		return shipmentRepository.save(shipment);
	}

	@Override
	@Transactional
	public Shipment updateShipment(UUID orderId, LocalDate shipmentDate, String methodName) {
		// TODO Auto-generated method stub
		return null;
	}

}
