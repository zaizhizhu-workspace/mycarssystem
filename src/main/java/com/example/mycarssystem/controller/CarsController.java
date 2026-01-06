package com.example.mycarssystem.controller;

import com.example.mycarssystem.entity.Cars;
import com.example.mycarssystem.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    @Autowired
    private CarsRepository carsRepository;

    @GetMapping
    public List<Cars> getAll() {
        return carsRepository.findAll();
    }

    @PostMapping
    public Cars add(@RequestBody Cars car) {
        return carsRepository.save(car);
    }
}