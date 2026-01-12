package com.example.mycarssystem.service.impl;

import com.example.mycarssystem.common.Result;
import com.example.mycarssystem.dto.CarsDTO;
import com.example.mycarssystem.entity.Cars;
import com.example.mycarssystem.entity.User;
import com.example.mycarssystem.repository.CarsRepository;
import com.example.mycarssystem.repository.UserRepository;
import com.example.mycarssystem.service.CarsService;
import com.example.mycarssystem.vo.CarsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarsServiceImpl(CarsRepository carsRepository, UserRepository userRepository) {
        this.carsRepository = carsRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CarsVO> getByVin(String vin) {
        return carsRepository.findFirstByVin(vin)
                .map(car -> {
                    CarsVO vo = new CarsVO();
                    vo.setId(car.getId());
                    vo.setCarName(car.getCarName());
                    vo.setCarType(car.getCarType());
                    vo.setVin(car.getVin());
                    vo.setLicensePlate(car.getLicensePlate());
                    if (car.getOwner() != null) {
                        vo.setOwnerId(car.getOwner().getId());
                        vo.setUserName(car.getOwner().getUserName());
                        vo.setPhoneNumber(car.getOwner().getPhoneNumber());
                    }
                    return ResponseEntity.ok(vo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Cars>> getByLicensePlate(String licensePlate) {
        List<Cars> cars = carsRepository.findByLicensePlate(licensePlate);
        if (cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }

    @Override
    @Transactional
    public Result<String> addCar(CarsDTO carsDTO) {
        if (carsRepository.existsByVin(carsDTO.getVin())) {
            return Result.error(400, "添加失败：数据库已经存在 VIN 为 " + carsDTO.getVin() + " 的记录");
        }
        
        if (carsRepository.existsByLicensePlate(carsDTO.getLicensePlate())) {
            return Result.error(400, "添加失败：车牌号 " + carsDTO.getLicensePlate() + " 已存在");
        }
        
        User owner = userRepository.findById(carsDTO.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("用户不存在，ID: " + carsDTO.getOwnerId()));
        
        Cars car = new Cars();
        BeanUtils.copyProperties(carsDTO, car);
        car.setOwner(owner);
        
        carsRepository.save(car);
        return Result.success("添加成功！VIN: " + car.getVin());
    }

    @Override
    @Transactional
    public Result<String> deleteByVin(String vin) {
        return carsRepository.findFirstByVin(vin)
                .map(car -> {
                    carsRepository.delete(car);
                    return Result.success("删除成功！VIN: " + vin);
                })
                .orElse(Result.error(404, "删除失败：数据库中不存在 VIN 为 " + vin + " 的记录"));
    }

    @Override
    @Transactional
    public Result<String> updateCar(String vin, CarsDTO carsDTO) {
        Cars existingCar = carsRepository.findFirstByVin(vin).orElse(null);
        if (existingCar == null) {
            return Result.error(404, "修改失败：数据库中不存在 VIN 为 " + vin);
        }

        if (!vin.equals(carsDTO.getVin()) && carsRepository.existsByVin(carsDTO.getVin())) {
            return Result.error(400, "修改失败：VIN 码已存在");
        }

        if (!existingCar.getLicensePlate().equals(carsDTO.getLicensePlate()) &&
                carsRepository.existsByLicensePlate(carsDTO.getLicensePlate())) {
            return Result.error(400, "修改失败：车牌号已存在");
        }

        User owner = userRepository.findById(carsDTO.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));

        BeanUtils.copyProperties(carsDTO, existingCar, "id", "owner");
        existingCar.setOwner(owner);
        carsRepository.save(existingCar);

        return Result.success("修改成功！");
    }
    
    @Override
    @Transactional(readOnly = true)
    public Result<Page<Cars>> listCars(Pageable pageable) {
        return Result.success(carsRepository.findAll(pageable));
    }
}
