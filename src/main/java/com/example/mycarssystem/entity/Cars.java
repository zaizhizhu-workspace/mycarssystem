package com.example.mycarssystem.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data // 自动生成 Getter/Setter/ToString
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(unique = true)
    private String vin;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;
}