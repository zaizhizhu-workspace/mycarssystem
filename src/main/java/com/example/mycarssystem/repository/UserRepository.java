package com.example.mycarssystem.repository;

import com.example.mycarssystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 重点：自定义根据 VIN 删除的方法
    @Transactional
    void deleteByIdCard(String idCard);

    // 顺便定义一个查询，用于校验是否存在
    boolean existsByIdCard(String idCard);

    // 顺便定义一个查询，用于校验是否存在
    List<User> findByIdCard(String idCard);

}