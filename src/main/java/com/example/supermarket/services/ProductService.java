package com.example.supermarket.services;

import com.example.supermarket.domain.entities.Product;

public interface ProductService {

    void addProduct(String productInput);

    Product getProduct(String name);
}
