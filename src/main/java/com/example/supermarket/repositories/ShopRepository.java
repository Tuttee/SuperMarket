package com.example.supermarket.repositories;

import com.example.supermarket.domain.entities.Category;
import com.example.supermarket.domain.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {
    Optional<Shop> findByAddress(String shopAddress);

    Optional<Shop> findByName(String shopName);
}
