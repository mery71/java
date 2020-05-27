package com.lacnt.clock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lacnt.clock.model.Category;

@Component
public interface CategoryService {
	List<Category> getAllCategory();

	Category findCategoryById(int id_category);

	void addCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(int id_category);
}