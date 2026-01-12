package com.example.mycarssystem.service;

import com.example.mycarssystem.entity.User;
import com.example.mycarssystem.vo.UserVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserVO> getByIdCard(String idCard);
    String addUser(User user);
    String deleteUser(String idCard);
    String updateUser(User user);
}
