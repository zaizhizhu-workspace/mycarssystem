package com.example.mycarssystem.repository;

import com.example.mycarssystem.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {

    // Spring Data 会根据方法名自动生成 SQL: SELECT * FROM cars WHERE license_plate = ?
    List<Cars> findByLicensePlate(String licensePlate);

    // 重点：自定义根据 VIN 删除的方法
    @Transactional
    void deleteByVin(String vin);

    // 顺便定义一个查询，用于校验是否存在
    boolean existsByVin(String vin);

    // 顺便定义一个查询，用于校验是否存在
    List<Cars> findByVin(String vin);

}