package com.example.mycarssystem.entity;

import com.example.mycarssystem.config.SnowflakeId;
import jakarta.persistence.*;
import lombok.Data;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @SnowflakeId
    @Column(name = "id", updatable = false, nullable = false)
    @JsonSerialize(using = ToStringSerializer.class) // 防止前端精度丢失
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "id_card", nullable = false, unique = true, length = 18)
    private String idCard;
}