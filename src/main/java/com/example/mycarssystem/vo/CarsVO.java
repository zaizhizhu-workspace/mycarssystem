package com.example.mycarssystem.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarsVO {
    private String id;

    private String carName;

    private String carType;

    private String vin;

    private String licensePlate;

    private String ownerId;

    private String userName;

    private String phoneNumber;

}
