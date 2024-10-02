package com.example.supermarket;

import com.example.supermarket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final CategoryService categoryService;
    private final TownService townService;
    private final ShopService shopService;
    private final SellerService sellerService;
    private final ProductService productService;

    @Autowired
    public Runner(CategoryService categoryService, TownService townService, ShopService shopService, SellerService sellerService, ProductService productService) {
        this.categoryService = categoryService;
        this.townService = townService;
        this.shopService = shopService;
        this.sellerService = sellerService;
        this.productService = productService;
    }


    @Override
    public void run(String... args) throws Exception {
        String menu = """
                Hi
                Choose option from:
                1 - for Add Category
                2 - for Add Town
                3 - for Add Shop
                4 - for Add Seller
                5 - for Add Product
                6 - for Set seller new manager
                7 - for Distributing product in shops
                8 - for Showing all sellers in shop
                9 - for Showing all products in shop""";
        System.out.println(menu);

        String input = scanner.nextLine();

        while (!input.equals("STOP")) {
            switch (input) {
                case "1" -> addCategory();
                case "2" -> addTown();
                case "3" -> addShop();
                case "4" -> addSeller();
                case "5" -> addProduct();
                case "6" -> addManager();
                case "7" -> addProductsDistribution();
                case "8" -> getShopSellers();
                case "9" -> getShopProducts();
                default -> System.out.println("Enter a valid choice!");
            }

            System.out.println(menu);

            input = scanner.nextLine();
        }
    }

    private void addCategory() {
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
        this.categoryService.addCategory(categoryName);
    }

    private void addTown() {
        System.out.println("Enter town name:");
        String townName = scanner.nextLine();
        this.townService.addTown(townName);
    }

    private void addShop() {
        System.out.println("Enter shop details in format: name address town");
        String shopInput = scanner.nextLine();
        this.shopService.addShop(shopInput);
    }

    private void addSeller() {
        System.out.println("Enter seller details in format: firstName lastName age salary shopName");
        String sellerInput = scanner.nextLine();
        this.sellerService.addSeller(sellerInput);
    }

    private void addProduct() {
        System.out.println("Enter product details in format: name price bestBefore(dd-MM-yyyy) category");
        String productInput = scanner.nextLine();
        this.productService.addProduct(productInput);
    }

    private void addManager() {
        System.out.println("Enter seller first and last names:");
        String sellerInput = scanner.nextLine();
        System.out.println("Enter manager first and last names:");
        String managerInput = scanner.nextLine();

        sellerService.addManager(sellerInput, managerInput);
    }

    private void addProductsDistribution() {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product distribution in Shops names in format [shopName1 shopName2 ...]:");
        String shopNames = scanner.nextLine();
        shopService.addProductToShops(productName, shopNames);
    }

    private void getShopSellers() {
        System.out.println("Enter shop name:");
        String shopName = scanner.nextLine();
        shopService.getShopSellers(shopName);
    }

    private void getShopProducts() {
        System.out.println("Enter shop name:");
        String shopName = scanner.nextLine();
        shopService.getShopProducts(shopName);
    }
}
