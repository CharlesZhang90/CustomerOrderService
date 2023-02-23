package com.lionsbot.demo.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="shipments")
@SQLDelete(sql = "UPDATE shipments SET deleted = true WHERE shipment_id=?")
@Where(clause = "deleted=false")
public class Shipment {

	@Id
	@GeneratedValue
	@Column(name="shipment_id")
	private UUID shipmentId;
	
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
	
	@Column(name="shipment_date")
	private LocalDate shipmentDate;

	@ManyToOne
	@JoinColumn(name="method")
	private Method method;
	
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;

}
