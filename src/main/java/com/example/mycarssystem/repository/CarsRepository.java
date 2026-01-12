package com.example.mycarssystem.repository;

import com.example.mycarssystem.entity.Cars;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Cars, String> {

    /**
     * 根据车牌号查询车辆列表
     */
    List<Cars> findByLicensePlate(String licensePlate);

    /**
     * 根据VIN码删除车辆
     */
    @Transactional
    void deleteByVin(String vin);

    /**
     * 检查VIN码是否存在
     */
    boolean existsByVin(String vin);
    
    /**
     * 检查车牌号是否存在
     */
    boolean existsByLicensePlate(String licensePlate);

    /**
     * 根据VIN码查询车辆列表
     */
    List<Cars> findByVin(String vin);
    
    /**
     * 获取第一个匹配VIN码的车辆
     */
    default Optional<Cars> findFirstByVin(String vin) {
        return findByVin(vin).stream().findFirst();
    }
    
    /**
     * 分页查询所有车辆
     */
    @Override
    Page<Cars> findAll(Pageable pageable);
}