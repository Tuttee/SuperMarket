package com.example.supermarket.repositories;

import com.example.supermarket.domain.entities.Category;
import com.example.supermarket.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findProductByName(String name);
}
