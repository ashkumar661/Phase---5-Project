package com.app.controller;

import java.util.List;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Products;
import com.app.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:8080/", "http://localhost:4200/" })
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	// List all products
	@GetMapping("/menu")
	public List<Products> getAllProducts() {
		return productRepository.findAll();
	}

	// Admin - Add a product
	@PostMapping("/admin/add-product")
	public ResponseEntity<Products> addProduct(@RequestBody Products newProduct) {
		return ResponseEntity.ok(productRepository.save(newProduct));
	}

	// Admin - Update product
	@PostMapping("/admin/update-product/{id}")
	public ResponseEntity<Products> updateProduct(@PathVariable int id, @RequestBody Products newProduct) {
		Products product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		product.setProductName(newProduct.getProductName());
		product.setPrice(newProduct.getPrice());
		product.setCategory(newProduct.getCategory());
		product.setDescription(newProduct.getDescription());
		Products updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

	// Admin - Delete product
	@DeleteMapping("/admin/delete-product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok("Product Deleted");
	}

	// Get product by product ID
	@GetMapping("/product/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable int id) {
		Products product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
		return ResponseEntity.ok(product);
	}
}
