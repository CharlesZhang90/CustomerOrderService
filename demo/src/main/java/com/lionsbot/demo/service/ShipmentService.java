package com.lionsbot.demo.service;

import java.time.LocalDate;
import java.util.UUID;

import com.lionsbot.demo.entity.Shipment;

public interface ShipmentService {
	
	public Shipment createShipment(UUID orderId, LocalDate shipmentDate, String methodName);
	
	public Shipment updateShipment(UUID orderId, LocalDate shipmentDate, String methodName);
	
}
