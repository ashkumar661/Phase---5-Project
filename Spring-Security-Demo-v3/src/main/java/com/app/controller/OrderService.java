package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.app.model.Cart;
import com.app.model.Order;
import com.app.model.OrderItems;
import com.app.repository.CartRepository;
import com.app.repository.OrderItemsRepository;
import com.app.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemsRepository orderItemsRepository;

	public void placeOrder(int id) {
		List<Cart> userCart = cartRepository.findAllByUserId(id);
		Order order = new Order();
		LocalDate date = LocalDate.now();
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String newDate = date.format(myFormat);
		order.setCreatedDate(newDate);
		int total = 0;
		for (Cart cartItems : userCart) {
			total += ((cartItems.getPrice()) * (cartItems.getQuantity()));
		}
		order.setTotalPrice(total);
		order.setUserId(id);
		orderRepository.save(order);
		for (Cart item : userCart) {
			OrderItems orderItems = new OrderItems();
			orderItems.setOrder(order);
			orderItems.setPrice(item.getPrice());
			orderItems.setProductId(item.getProductId());
			orderItems.setProductName(item.getProductName());
			orderItems.setQuantity(item.getQuantity());
			orderItems.setUserId(item.getUserId());
			orderItemsRepository.save(orderItems);
		}
	}
}
