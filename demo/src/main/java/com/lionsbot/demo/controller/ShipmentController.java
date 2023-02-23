package com.lionsbot.demo.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lionsbot.demo.service.ShipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ShipmentController {
	
	private final ShipmentService shipmentService;
		
	//allow admin to create an shipment
	@PostMapping("/shipments/{orderId}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> createShipment(@PathVariable UUID orderId, @RequestParam("shipmentDate") LocalDate shipmentDate, @RequestParam("methodName") String methodName) {
		shipmentService.createShipment(orderId, shipmentDate, methodName);
		return ResponseEntity.ok().body("Shipment created.");
	}
}
