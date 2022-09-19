package com.app.repository;

import java.util.List;

import com.app.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findAllByUserId(int userId);
}
