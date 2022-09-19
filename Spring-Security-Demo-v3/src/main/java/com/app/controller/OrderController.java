package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.model.Cart;
import com.app.model.Order;
import com.app.model.OrderItems;
import com.app.repository.CartRepository;
import com.app.repository.OrderItemsRepository;
import com.app.repository.OrderRepository;
import com.app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:8080/", "http://localhost:4200/" })
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemsRepository orderItemsRepository;

	// Place order
	@GetMapping("/place-order/{id}")
	public ResponseEntity<?> placeOrder(@PathVariable int id) {
//		List<Cart> userCart = cartRepository.findAllByUserId(id);
		orderService.placeOrder(id);
		List<Cart> userCart= cartRepository.findAllByUserId(id);
		for(Cart cartItems: userCart) {
			cartRepository.delete(cartItems);
		}
		Map<String, Boolean> response = new HashMap<>();
		response.put("Order Placed",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	// Find all orders by userId
	@GetMapping("/view-all-order/{id}")
	public ResponseEntity<List<Order>> viewAllOrder(@PathVariable int id) {
		System.out.println("Inside view all order API");
		List<Order> userOrders = orderRepository.findAllByUserId(id);
		System.out.println("List of user orders: " + userOrders);
		return ResponseEntity.ok(userOrders);
	}
	
	// Find order by orderId
		@GetMapping("/view-order/{id}")
		public ResponseEntity<List<OrderItems>> viewOrder(@PathVariable int id) {
			List<OrderItems> orderItems = orderItemsRepository.findAllByOrderId(id);
			return ResponseEntity.ok(orderItems);
		}
}
