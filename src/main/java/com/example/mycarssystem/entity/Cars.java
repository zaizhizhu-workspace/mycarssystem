package com.example.mycarssystem.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @Column(name = "car_type")
    private String carType;

    @Column(unique = true)
    private String vin;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    // name 是本表(cars)的字段，referencedColumnName 是对方表(users)的主键名
    private User owner;

}