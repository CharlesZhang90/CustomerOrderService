package com.lionsbot.demo.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="methods")
@SQLDelete(sql = "UPDATE methods SET deleted = true WHERE method=?")
@Where(clause = "deleted=false")
public class Method {

	@Id
	@GeneratedValue
	@Column(name="method")
	private UUID method;
	
	@Column(name="method_name")
	private String methodName;
	
	@OneToMany(mappedBy="method")
	private List<Shipment> shipments;
	
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;

}