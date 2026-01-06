package com.example.mycarssystem.controller;

import com.example.mycarssystem.entity.User;
import com.example.mycarssystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getByName/{vin}")
    public List<User> getByVin(@PathVariable String vin) {
        if (userRepository.existsByIdCard(vin)) {
            return userRepository.findByIdCard(vin);
        } else {
            return null;
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        String idCard = user.getIdCard();
        if (!userRepository.existsByIdCard(idCard)) {
            userRepository.save(user);
            return "添加成功！用户名: " + user.getUserName();
        } else {
            return "添加失败：数据库已经存在客戶 " + user.getUserName() + " 的数据";
        }
    }

    @PostMapping("/delete")
    public String deleteByVin(@RequestParam String idCard) {
        if (userRepository.existsByIdCard(idCard)) {
            userRepository.existsByIdCard(idCard);
            return "删除成功！身份证: " + idCard;
        } else {
            return "删除失败：数据库中不存在身份证为 " + idCard + " 的记录";
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody User car) {
        String idCard = car.getIdCard();
        if (userRepository.existsByIdCard(idCard)) {
            List<User> carList = userRepository.findByIdCard(idCard);
            // 因为已经验证过存在，并且vin唯一所以可以直接拿
            User oldCar = carList.get(0);
            car.setId(oldCar.getId());
            userRepository.save(car);
            return "修改成功！不存在身份证为: " + idCard;
        } else {
            return "修改失败：数据库中不存在身份证为 " + idCard + " 的记录";
        }
    }
}