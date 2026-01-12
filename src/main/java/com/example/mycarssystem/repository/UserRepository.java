package com.example.mycarssystem.repository;

import com.example.mycarssystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 根据身份证号删除用户
    @Transactional
    void deleteByIdCard(String idCard);

    // 检查身份证号是否存在
    boolean existsByIdCard(String idCard);

    // 根据身份证号查询用户
    List<User> findByIdCard(String idCard);
    
    // 根据ID查询用户
    @Override
    Optional<User> findById(String id);
}