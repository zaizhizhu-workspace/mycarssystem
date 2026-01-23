package com.example.mycarssystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarsVO {
    private Long id;

    private String carName;

    private String carType;
    
    private String vin;

    private String licensePlate;

    private Long ownerId;

    private String userName;

    private String phoneNumber;

}
