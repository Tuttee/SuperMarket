package com.example.supermarket.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Shop extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private Set<Seller> seller;

    @ManyToMany(mappedBy = "shops", fetch = FetchType.EAGER)
    private Set<Product> products;
}

