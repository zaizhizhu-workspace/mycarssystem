package com.example.mycarssystem.service.impl;

import com.example.mycarssystem.entity.User;
import com.example.mycarssystem.repository.UserRepository;
import com.example.mycarssystem.service.UserService;
import com.example.mycarssystem.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserVO> getByIdCard(String idCard) {
        return userRepository.findByIdCard(idCard).stream()
                .findFirst()
                .map(user -> {
                    UserVO vo = new UserVO();
                    vo.setId(user.getId());
                    vo.setUserName(user.getUserName());
                    vo.setPhoneNumber(user.getPhoneNumber());
                    vo.setIdCard(user.getIdCard());
                    return ResponseEntity.ok(vo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public String addUser(User user) {
        String idCard = user.getIdCard();
        if (!userRepository.existsByIdCard(idCard)) {
            userRepository.save(user);
            return "添加成功！用户名: " + user.getUserName();
        }
        return "添加失败：数据库已经存在客户 " + user.getUserName() + " 的数据";
    }

    @Override
    @Transactional
    public String deleteUser(String idCard) {
        if (userRepository.existsByIdCard(idCard)) {
            userRepository.deleteByIdCard(idCard);
            return "删除成功！身份证: " + idCard;
        }
        return "删除失败：数据库中不存在身份证为 " + idCard + " 的记录";
    }

    @Override
    @Transactional
    public String updateUser(User user) {
        String idCard = user.getIdCard();
        return userRepository.findByIdCard(idCard).stream()
                .findFirst()
                .map(existingUser -> {
                    user.setId(existingUser.getId());
                    userRepository.save(user);
                    return "修改成功！身份证: " + idCard;
                })
                .orElse("修改失败：数据库中不存在身份证为 " + idCard + " 的记录");
    }
}
