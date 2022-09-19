package com.app.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "order")
@Table(name = "order_items")
public class OrderItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int productId, userId,quantity, price;

	private String productName;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
