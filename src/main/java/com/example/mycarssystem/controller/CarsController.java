package com.example.mycarssystem.controller;

import com.example.mycarssystem.entity.Cars;
import com.example.mycarssystem.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    @Autowired
    private CarsRepository carsRepository;

    @GetMapping("/getByVin/{vin}")
    public List<Cars> getByVin(@PathVariable String vin) {
        return carsRepository.findByVin(vin);
    }

    @GetMapping("/getByLicensePlate/{licensePlate}")
    public List<Cars> getByLicensePlate(@PathVariable String licensePlate) {
        return carsRepository.findByLicensePlate(licensePlate);
    }

    @PostMapping("/add")
    public String add(@RequestBody Cars car) {
        String vin = car.getVin();
        if (!carsRepository.existsByVin(vin)) {
            carsRepository.save(car);
            return "添加成功！VIN: " + vin;
        } else {
            return "添加失败：数据库已经存在 VIN 为 " + vin + " 的记录";
        }
    }

    @PostMapping("/delete")
    public String deleteByVin(@RequestParam String vin) {
        if (carsRepository.existsByVin(vin)) {
            carsRepository.deleteByVin(vin);
            return "删除成功！VIN: " + vin;
        } else {
            return "删除失败：数据库中不存在 VIN 为 " + vin + " 的记录";
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody Cars car) {
        String vin = car.getVin();
        if (carsRepository.existsByVin(vin)) {
            List<Cars> carList = carsRepository.findByVin(vin);
            // 因为已经验证过存在，并且vin唯一所以可以直接拿
            Cars oldCar = carList.get(0);
            car.setId(oldCar.getId());
            carsRepository.save(car);
            return "修改成功！VIN: " + vin;
        } else {
            return "修改失败：数据库中不存在 VIN 为 " + vin + " 的记录";
        }
    }
}