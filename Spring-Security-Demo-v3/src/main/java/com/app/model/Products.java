package com.app.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	private String productName, category, description;
	private int price;

	public Products(String productName, String category, int price, String description) {
		super();
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	public Products() {
		super();
	}
}
