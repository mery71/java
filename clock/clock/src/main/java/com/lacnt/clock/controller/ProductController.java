package com.lacnt.clock.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lacnt.clock.model.Product;
import com.lacnt.clock.service.ProductService;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}

	@PostMapping("/product")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}

	@PutMapping("/product/{id}")
	public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		Product product1 = productService.findProductById(id);
		product1.setName(product.getName());
		product1.setPrice(product.getPrice());
		product1.setQuantity(product.getQuantity());
		product1.setIsdelete(product.getIsdelete());
		product1.setId_category(product.getId_category());
		productService.updateProduct(product1);
		return product1;

	}
	@PutMapping("/product/delete/{id}")
	public void deleteProduct(@PathVariable int id)  {
		productService.deleteProduct(id);

	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable int id) {
		try {
			Product product = productService.findProductById(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping("/product/export/excel")
	public ResponseEntity<InputStreamResource> exportAllData() throws IOException{
//		Authentication authencation=SecurityContextHolder.getContext().getAuthentication();
//		List<Product> products=(List<Product>) productService.findProductById(id_product)
//		ByteArrayInputStream bais=CreateExcel.
		ByteArrayInputStream stream= productService.exportAllData();
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition","attachment; filename=products.xls");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
//		
	}
	

	}

