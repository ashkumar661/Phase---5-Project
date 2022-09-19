package com.app.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	private int productId, userId, quantity, price;

	private String productName;

	public Cart(int productId, String productName, int quantity, int price, int userId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.userId = userId;
	}

	public Cart() {
		super();
	}
}
