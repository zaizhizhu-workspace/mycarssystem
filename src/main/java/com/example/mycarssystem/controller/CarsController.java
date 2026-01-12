package com.example.mycarssystem.controller;

import com.example.mycarssystem.common.Result;
import com.example.mycarssystem.dto.CarsDTO;
import com.example.mycarssystem.entity.Cars;
import com.example.mycarssystem.service.CarsService;
import com.example.mycarssystem.vo.CarsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆管理控制器
 */
@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private final CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    /**
     * 根据车架号查询车辆信息
     */
    @GetMapping("/{vin}")
    public ResponseEntity<CarsVO> getByVin(@PathVariable String vin) {
        return carsService.getByVin(vin);
    }

    /**
     * 根据车牌号查询车辆信息
     */
    @GetMapping("/license-plate/{licensePlate}")
    public ResponseEntity<List<Cars>> getByLicensePlate(@PathVariable String licensePlate) {
        return carsService.getByLicensePlate(licensePlate);
    }

    /**
     * 添加车辆
     */
    @PostMapping
    public Result<String> add(@Validated @RequestBody CarsDTO carsDTO) {
        return carsService.addCar(carsDTO);
    }

    /**
     * 删除车辆
     */
    @DeleteMapping("/{vin}")
    public Result<String> deleteByVin(@PathVariable String vin) {
        return carsService.deleteByVin(vin);
    }

    /**
     * 更新车辆信息
     */
    @PutMapping("/{vin}")
    public Result<String> update(@PathVariable String vin, @Validated @RequestBody CarsDTO carsDTO) {
        return carsService.updateCar(vin, carsDTO);
    }

    /**
     * 分页查询车辆列表
     */
    @GetMapping
    public Result<Page<Cars>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createTime") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        return carsService.listCars(pageable);
    }
}