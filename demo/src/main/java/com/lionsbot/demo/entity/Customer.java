package com.lionsbot.demo.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE customer_id=?")
@Where(clause = "deleted=false")
public class Customer implements UserDetails {

	private static final long serialVersionUID = -1990174327471294489L;

	@Id
	@GeneratedValue
	@Column(name="customer_id")
	private UUID customerId;
	
	@Column(name="customer_name")
	@NotNull
	private String customerName;
	
	@NotNull
	@Email(message = "Invalid Email addresss.")
	@Column(name="email")
	private String email;
	
	@NotNull
	@Column(name="password")
	private String password;
	
	@NotNull
	@Column(name="contact_no")
	@Size(min = 8, max = 8, message = "Contact Number should be within 8 digits.")
	private String contactNo;
	
	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
	private List<Order> orders;
	
	@OneToOne(mappedBy = "customer", cascade=CascadeType.ALL)
	private Address address;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	@NotNull
	private Role role;
	
	@NotNull
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.role.name()));
	}

	@Override
	public String getUsername() {
		return this.customerName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}