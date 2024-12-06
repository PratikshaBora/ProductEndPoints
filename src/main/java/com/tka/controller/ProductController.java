package com.tka.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entity.Product;
import com.tka.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello, welcome to the product management system";		
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product p) {
		
		service.addProduct(p);
		return "Product added successfully";
		
	}
	
	@PutMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable int id,@RequestBody Product p) {
		service.updateProduct(p,id);
		return "Product updated successfully";
	}
	
	@GetMapping("/productList")
	public List<Product> showProductList(){
		return service.showProductList();
	}
	
	@DeleteMapping("/deleteProduct")
	public String deleteProduct(@RequestParam int id) {
		return service.deleteProduct(id);
	}
	
	@GetMapping("/showProduct")
	public Product showProductById(@RequestParam int id) {
		return service.showProductById(id);
	}
	
	@GetMapping("/showElectronicItem")
	public List<Product> showElectronicItem(){
		return service.showElectronicItem();
	}
	
	@GetMapping("/categoryWiseProductList")
	public Map<String,ArrayList<Product>> showCategoryWiseProductList(){
		return service.showCategoryWiseProductList();
	}
}
