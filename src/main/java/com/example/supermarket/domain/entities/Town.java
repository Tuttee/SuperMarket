package com.example.supermarket.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "towns")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Town extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
}

