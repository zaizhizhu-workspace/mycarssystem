package com.example.mycarssystem.service;

import com.example.mycarssystem.common.Result;
import com.example.mycarssystem.dto.CarsDTO;
import com.example.mycarssystem.entity.Cars;
import com.example.mycarssystem.vo.CarsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarsService {
    ResponseEntity<CarsVO> getByVin(String vin);
    
    ResponseEntity<List<Cars>> getByLicensePlate(String licensePlate);
    
    Result<String> addCar(CarsDTO carsDTO);
    
    Result<String> deleteByVin(String vin);
    
    Result<String> updateCar(String vin, CarsDTO carsDTO);
    
    Result<Page<Cars>> listCars(Pageable pageable);
}
