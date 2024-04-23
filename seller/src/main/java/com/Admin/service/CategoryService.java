package com.Admin.service;

import java.util.List;

import com.Admin.entity.Category;

public interface CategoryService {
	Category add(Category c);
	List<Category> getCategoriesByStatusIn(List<String> status);
	boolean delete(int id);
	Category update(Category c);
}
