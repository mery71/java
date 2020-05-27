package com.lacnt.clock.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lacnt.clock.model.Product;
@Component
public interface ProductService {
	List<Product> getAllProduct();

	Product findProductById(int id_product);

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int id_product);
	ByteArrayInputStream exportAllData() throws IOException;

	//Product productCategory(int id);
}
