package com.lionsbot.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lionsbot.demo.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

}
