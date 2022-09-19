package com.app.repository;

import java.util.List;

import com.app.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	List<Cart> findAllByUserId(int userId);
}
