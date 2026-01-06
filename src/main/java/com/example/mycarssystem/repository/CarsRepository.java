package com.example.mycarssystem.repository;

import com.example.mycarssystem.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {

    // Spring Data 会根据方法名自动生成 SQL: SELECT * FROM cars WHERE license_plate = ?
    Optional<Cars> findByLicensePlate(String licensePlate);
}