package com.example.mycarssystem.dto;

import lombok.Data;



/**
 * 车辆数据传输对象
 */
@Data
public class CarsDTO {

    private String carName;

    private String carType;

    private String vin;

    private String licensePlate;

    private String ownerId;  // 前端只传ownerId
}
