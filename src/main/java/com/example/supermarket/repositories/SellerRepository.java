package com.example.supermarket.repositories;

import com.example.supermarket.domain.entities.Category;
import com.example.supermarket.domain.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
    Optional<Seller> getSellerByFirstNameAndLastName(String firstName, String lastName);
}
