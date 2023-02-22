package com.lionsbot.demo.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE order_id=?")
@Where(clause = "deleted=false")
public class Order {

	@Id
	@GeneratedValue
	@Column(name="order_id")
	private UUID orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@NotNull
	private Customer customer;
	
	@Column(name="order_date")
	@NotNull
	private LocalDate orderDate;
	
	@Column(name="total_price")
	@NotNull
	private double totalPrice;
	
	@Column(name="no_of_items")
	@NotNull
	private int numberOfItems;
	
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
	private List<Shipment> shipments;
	
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;
		
}