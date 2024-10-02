package com.example.supermarket.services;

import com.example.supermarket.domain.entities.Shop;

import java.util.Optional;

public interface ShopService {
    void addShop(String input);

   Optional<Shop> findShopByName(String s);

    void addProductToShops(String productName, String shops);

    void getShopSellers(String shopName);

    void getShopProducts(String shopName);
}
