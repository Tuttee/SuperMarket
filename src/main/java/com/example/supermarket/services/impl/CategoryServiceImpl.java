package com.example.supermarket.services.impl;

import com.example.supermarket.domain.entities.Category;
import com.example.supermarket.repositories.CategoryRepository;
import com.example.supermarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(String name) {
        if (name.length() < 2) {
            System.out.println("Name must be at minimum two characters!");
        } else if (categoryRepository.findByName(name).isPresent()) {
            System.out.println("Category exists");
        } else {
            Category category = new Category(name);

            categoryRepository.saveAndFlush(category);
            System.out.println("Successfully added category!");
        }
    }

    @Override
    public Optional<Category> findCategoryByName(String category) {
        return categoryRepository.findByName(category);
    }
}
