package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Cart;
import com.app.model.Products;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;
	
	int qty;

	// Add a product to cart
	@RequestMapping("/add-to-cart/{id}")
	public ResponseEntity<Cart> addToCart(@PathVariable int id, @RequestBody Cart cart) {
		Products product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		Cart tempCart = new Cart();
		tempCart.setProductId(product.getProductId());
		tempCart.setProductName(product.getProductName());
		tempCart.setPrice(product.getPrice());
		tempCart.setQuantity(cart.getQuantity());
		tempCart.setUserId(cart.getUserId());
		return ResponseEntity.ok(cartRepository.save(tempCart));
	}
	
	// Increase cart items by cart Id
	@PutMapping("/increase-cart-item")
	public ResponseEntity<Cart> increaseCartItem(@RequestBody Cart cart) {
		System.out.println("Inside Update Cart API");
		qty = cart.getQuantity();
		if(qty>=5) {
			Cart tempCart= cartRepository.findById(cart.getCartId())
					.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: "));
			System.out.println("Temp Cart details: " + tempCart);
			tempCart.setQuantity(qty);
			Cart updatedCart = cartRepository.save(tempCart);
			System.out.println("Updated Cart details: "+ updatedCart);
			return ResponseEntity.ok(updatedCart);
		}
		else {
			qty++;
			Cart tempCart= cartRepository.findById(cart.getCartId())
					.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: "));
			System.out.println("Temp Cart details: " + tempCart);
			tempCart.setQuantity(qty);
			Cart updatedCart = cartRepository.save(tempCart);
			System.out.println("Updated Cart details: "+ updatedCart);
			return ResponseEntity.ok(updatedCart);
		}
	}
	
	// Decrease cart items by cart Id
	@PutMapping("/decrease-cart-item")
	public ResponseEntity<Cart> decreaseCartItem(@RequestBody Cart cart) {
		System.out.println("Inside Update Cart API");
		qty = cart.getQuantity();
		if(qty<=1) {
			Cart tempCart= cartRepository.findById(cart.getCartId())
					.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: "));
			System.out.println("Temp Cart details: " + tempCart);
			tempCart.setQuantity(qty);
			Cart updatedCart = cartRepository.save(tempCart);
			System.out.println("Updated Cart details: "+ updatedCart);
			return ResponseEntity.ok(updatedCart);
		}
		else {
			qty--;
			Cart tempCart= cartRepository.findById(cart.getCartId())
					.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: "));
			System.out.println("Temp Cart details: " + tempCart);
			tempCart.setQuantity(qty);
			Cart updatedCart = cartRepository.save(tempCart);
			System.out.println("Updated Cart details: "+ updatedCart);
			return ResponseEntity.ok(updatedCart);
		}
		
	}
	
	// Delete cart items by cart Id
	@DeleteMapping("/delete-cart-item/{id}")
	public ResponseEntity<?> deleteCartItem(@PathVariable int id) {
		System.out.println("Inside delete Cart API");
		cartRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Record Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	// View cart by User Id
	@RequestMapping("/user-cart/{id}")
	public ResponseEntity<List<Cart>> userCart(@PathVariable int id) {
		System.out.println("Inside user Cart API");
		List<Cart> cartItems = cartRepository.findAllByUserId(id);
		return ResponseEntity.ok(cartItems);
	}
	
}
