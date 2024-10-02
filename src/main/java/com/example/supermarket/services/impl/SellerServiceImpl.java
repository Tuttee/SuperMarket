package com.example.supermarket.services.impl;

import com.example.supermarket.domain.entities.Seller;
import com.example.supermarket.domain.entities.Shop;
import com.example.supermarket.repositories.SellerRepository;
import com.example.supermarket.services.SellerService;
import com.example.supermarket.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ShopService shopService;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ShopService shopService) {
        this.sellerRepository = sellerRepository;
        this.shopService = shopService;
    }

    @Override
    public void addSeller(String input) {
        String[] inputSplit = input.split(" ");

        if (inputSplit.length != 5) {
            System.out.println("Enter a valid input: FirstName LastName Age Salary shopName!");
            return;
        }

        String firstName = inputSplit[0];
        String lastName = inputSplit[1];
        if (firstName.trim().length() < 2 || lastName.trim().length() < 2) {
            System.out.println("First and Last name must be at least two characters!");
            return;
        }

        int age = Integer.parseInt(inputSplit[2]);
        if (age < 18 || age > 100) {
            System.out.println("Seller must be at least 18 years old and at most 99!");
            return;
        }

        BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(inputSplit[3]));
        if (salary.compareTo(BigDecimal.valueOf(0)) <= 0) {
            System.out.println("Salary must be a positive number");
            return;
        }


        Optional<Shop> shop = shopService.findShopByName(inputSplit[4]);
        if (shop.isEmpty()) {
            System.out.println("Shop not found!");
            return;
        }

        Seller seller = new Seller();
        seller.setFirstName(firstName);
        seller.setLastName(lastName);
        seller.setAge(age);
        seller.setSalary(salary);
        seller.setShop(shop.get());

        sellerRepository.saveAndFlush(seller);

        System.out.println("Successfully added seller!");
    }

    @Override
    public void addManager(String sellerNames, String managerNames) {
        String[] sellerNamesArr = sellerNames.split(" ");
        String[] managerNamesArr = managerNames.split(" ");

        if (sellerNamesArr.length != 2 || managerNamesArr.length != 2) {
            System.out.println("Please enter correct input for both Seller and Manager: firstName lastName");
            return;
        }

        Optional<Seller> seller = sellerRepository.getSellerByFirstNameAndLastName(sellerNamesArr[0], sellerNamesArr[1]);
        Optional<Seller> manager = sellerRepository.getSellerByFirstNameAndLastName(managerNamesArr[0], managerNamesArr[1]);

        if (seller.isEmpty() || manager.isEmpty()) {
            System.out.println("Seller or manager not found!");
            return;
        }

        seller.get().setManager(manager.get());

        sellerRepository.saveAndFlush(seller.get());
        System.out.println("Successfully added manager");
    }
}
