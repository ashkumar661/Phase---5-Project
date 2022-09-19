package com.app.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String name, address, email, password;

	public User(String name, String address, String email, String password) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}
}
