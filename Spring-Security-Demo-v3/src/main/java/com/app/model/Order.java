package com.app.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "orderItems")
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String createdDate;

	private int totalPrice, userId;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<OrderItems> orderItems;

	public Order() {
	}
}
