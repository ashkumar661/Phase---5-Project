package com.app.repository;

import java.util.List;

import com.app.model.OrderItems;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
	List<OrderItems> findAllByOrderId(int orderId);
}
