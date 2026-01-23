package com.example.mycarssystem.entity;
import com.example.mycarssystem.config.SnowflakeId;
import jakarta.persistence.*;
import lombok.Data;

import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "cars")
@Data
public class Cars {

    @Id
    @SnowflakeId
    @Column(name = "id", updatable = false, nullable = false)
    @JsonSerialize(using = ToStringSerializer.class) // 防止前端精度丢失
    private Long id;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @Column(name = "car_type")
    private String carType;

    @Column(unique = true)
    private String vin;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    // name 是本表(cars)的字段，referencedColumnName 是对方表(users)的主键名
    private User owner;

}