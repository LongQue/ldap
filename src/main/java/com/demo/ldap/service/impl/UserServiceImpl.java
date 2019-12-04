package com.demo.ldap.service.impl;

import com.demo.ldap.dao.UserRepository;
import com.demo.ldap.entity.User;
import com.demo.ldap.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhouFufeng
 * @since 2019/11/28
 **/
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getAllPersonNames() {
        return ((List<User>) userRepository.findAll()).stream().map(User::getUserName).collect(Collectors.toList());
    }
    
    @Override
    public List<User> getAllPersons() {
        return ((List<User>)userRepository.findAll());
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

}
