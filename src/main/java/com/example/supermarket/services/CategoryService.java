package com.example.supermarket.services;

import com.example.supermarket.domain.entities.Category;

import java.util.Optional;

public interface CategoryService {

    void addCategory(String name);

    Optional<Category> findCategoryByName(String category);
}
