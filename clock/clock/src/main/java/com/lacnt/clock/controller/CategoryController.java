package com.lacnt.clock.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lacnt.clock.model.Category;
import com.lacnt.clock.service.CategoryService;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/category")
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}

	@PutMapping("/category/delete/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);

	}

	@PostMapping("/category")
	public void addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
	}

	@PutMapping("/category/{id}")
	public Category updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
		Category category1= categoryService.findCategoryById(id);
		category1.setName(category.getName());
		categoryService.updateCategory(category1);
		return category1;
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<Category> findCategory(@PathVariable int id) {
		try {
			Category cate = categoryService.findCategoryById(id);
			return new ResponseEntity<Category>(cate, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}

	}

}
