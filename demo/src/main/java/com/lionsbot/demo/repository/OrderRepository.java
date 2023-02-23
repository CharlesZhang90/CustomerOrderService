package com.lionsbot.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lionsbot.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
