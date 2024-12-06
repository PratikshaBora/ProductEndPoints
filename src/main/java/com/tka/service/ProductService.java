package com.tka.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.entity.Product;
import com.tka.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository repository;

	public void addProduct(Product p) {
		repository.save(p);
	}

	public void updateProduct(Product p, int id) {
		Optional<Product> existingProd = repository.findById(id);

		if (existingProd.isPresent()) {
			Product newPro = existingProd.get();

			newPro.setPname(p.getPname());
			newPro.setCategory(p.getCategory());
			newPro.setPrice(p.getPrice());
			newPro.setQuantity(p.getQuantity());

			repository.save(newPro);
		} else {
			throw new RuntimeException("Product with ID " + id + " not found");
		}
	}

	public List<Product> showProductList() {

		return repository.findAll();
	}

	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "Product deleted successfully";
	}

	public Product showProductById(int id) {
		Product p = repository.findById(id).get();

		return p;
	}

	public List<Product> showElectronicItem() {
		List<Product> plist = showProductList();

//		List<Product> elist = new ArrayList<Product>();
//		
//		for(Product p : plist) {
//			if(p.getCategory().equalsIgnoreCase("electronics")) {
//				elist.add(p);
//			}
//		}

		List<Product> elist = plist.stream().filter(p -> "Electronics".equalsIgnoreCase(p.getCategory())).toList();

		return elist;
	}

	public Map<String, ArrayList<Product>> showCategoryWiseProductList() {

		List<Product> plist = showProductList();

		/*
		 * // find the list of category from product list List<String> categoryList =
		 * plist.stream().map(Product::getCategory).distinct().toList();
		 * 
		 * Map<String,ArrayList<Product>> categoryProductList = new
		 * HashMap<String,ArrayList<Product>>();
		 * 
		 * for(String s : categoryList) { 
		 * 		ArrayList<Product> cPList = new ArrayList<Product>();
		 * 
		 * for(Product p: plist) { 
		 * 		if(s.equalsIgnoreCase(p.getCategory())) {
		 * 			 cPList.add(p); 
		 * 		} 
		 * } 
		 * 
		 * 		categoryProductList.put(s, cPList); 
		 * }
		 */

		// Group products by category into a map
		Map<String, ArrayList<Product>> categoryProductList = plist.stream()
				.collect(Collectors.groupingBy(Product::getCategory, // Group by category
						Collectors.toCollection(ArrayList::new) // Collect values into an ArrayList
				));
		return categoryProductList;
	}

}
