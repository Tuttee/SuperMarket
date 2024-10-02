package com.example.supermarket.services;

import com.example.supermarket.domain.entities.Town;

import java.util.Optional;

public interface TownService {

    void addTown(String name);

    Optional<Town> getTownByName(String townName);
}
