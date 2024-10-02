package com.example.supermarket.services.impl;

import com.example.supermarket.domain.entities.Town;
import com.example.supermarket.repositories.TownRepository;
import com.example.supermarket.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void addTown(String name) {
        if (townRepository.findByName(name).isPresent()) {
            System.out.println("Town already exists!");
        } else {
            Town town = new Town(name);
            townRepository.saveAndFlush(town);
            System.out.println("Successfully added town!");
        }
    }

    @Override
    public Optional<Town> getTownByName(String townName) {
        return townRepository.findByName(townName);
    }
}
