package com.example.supermarket.services.impl;

import com.example.supermarket.domain.entities.Category;
import com.example.supermarket.domain.entities.Product;
import com.example.supermarket.repositories.ProductRepository;
import com.example.supermarket.services.CategoryService;
import com.example.supermarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(String productInput) {
        String[] inputSplit = productInput.split(" ");

        if (inputSplit.length != 4) {
            System.out.println("Enter a valid input: Name Price BestBefore(dd-MM-yyyy) category!");
            return;
        }

        String productName = inputSplit[0];
        if (productName.trim().length() < 2) {
            System.out.println("Product name must be at least two characters!");
            return;
        }

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(inputSplit[1]));
        if (price.compareTo(BigDecimal.ZERO) < 1) {
            System.out.println("Price must be a positive number!");
            return;
        }

        LocalDate bestBefore = LocalDate.parse(inputSplit[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String categoryInput = inputSplit[3];
        Optional<Category> category = categoryService.findCategoryByName(categoryInput);
        if (category.isEmpty()) {
            System.out.println("Category not found!");
            return;
        }

        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        product.setBestBefore(bestBefore);
        product.setCategory(category.get());

        productRepository.saveAndFlush(product);
        System.out.println("Successfully added product!");
    }

    @Override
    public Product getProduct(String name) {
        Optional<Product> product = productRepository.findProductByName(name);
        if (product.isEmpty()) {
            throw new NoSuchElementException("Product not found");
        }

        return product.get();
    }
}
