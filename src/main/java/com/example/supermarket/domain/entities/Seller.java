package com.example.supermarket.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sellers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Seller extends BaseEntity{
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private BigDecimal salary;

    @ManyToOne(optional = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Seller manager;
}

