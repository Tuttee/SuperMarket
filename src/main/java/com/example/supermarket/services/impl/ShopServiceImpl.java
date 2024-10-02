package com.example.supermarket.services.impl;

import com.example.supermarket.domain.entities.Product;
import com.example.supermarket.domain.entities.Shop;
import com.example.supermarket.domain.entities.Town;
import com.example.supermarket.repositories.ProductRepository;
import com.example.supermarket.repositories.ShopRepository;
import com.example.supermarket.services.ProductService;
import com.example.supermarket.services.ShopService;
import com.example.supermarket.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final TownService townService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownService townService, ProductService productService, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.townService = townService;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public void addShop(String input) {
        String[] splitInput = input.split(" ");
        if (splitInput.length != 3) {
            System.out.println("Enter a valid input: Name Address Town!");
            return;
        }


        String shopName = splitInput[0];
        if (shopName.length() < 2) {
            System.out.println("Store name must be at least two characters!");
            return;
        }

        String shopAddress = splitInput[1];
        if (shopAddress.length() < 2) {
            System.out.println("Address must be at least two characters!");
            return;
        }

        Optional<Town> town = townService.getTownByName(splitInput[2]);
        if (town.isEmpty()) {
            System.out.println("Town cannot be found!");
            return;
        }

        if (shopRepository.findByAddress(shopAddress).isPresent()) {
            System.out.println("Shop already exists!");
        } else {
            Shop shop = new Shop();
            shop.setName(shopName);
            shop.setTown(town.get());
            shop.setAddress(shopAddress);

            shopRepository.saveAndFlush(shop);
            System.out.println("Successfully added shop!");
        }


    }

    @Override
    public Optional<Shop> findShopByName(String shopName) {
        return shopRepository.findByName(shopName);
    }

    @Override
    public void addProductToShops(String productName, String shops) {

        try {
            Product product = productService.getProduct(productName);


            Set<Shop> shopsByName = new HashSet<>();

            Arrays.stream(shops.split(" ")).forEach(s -> {
                Optional<Shop> shop = shopRepository.findByName(s);
                shop.ifPresent(shopsByName::add);
            });

            shopsByName.forEach(s -> {
                s.getProducts().add(product);
                product.getShops().add(s);
            });
            shopRepository.saveAllAndFlush(shopsByName);
            productRepository.saveAndFlush(product);


            System.out.println("Successfully added product distribution!");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getShopSellers(String shopName) {
        Optional<Shop> shop = shopRepository.findByName(shopName);

        shop.ifPresent(value -> value.getSeller().forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName())));
    }

    @Override
    public void getShopProducts(String shopName) {
        Optional<Shop> shop = shopRepository.findByName(shopName);

        shop.ifPresent(value -> value.getProducts().forEach(p -> System.out.println(p.getName() + " - " + p.getPrice())));
        if (shop.isEmpty()) System.out.println(shopName + " not found!");
    }
}
