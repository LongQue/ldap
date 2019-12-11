package com.demo.ldap.test;

import com.alibaba.fastjson.JSON;
import com.demo.ldap.ApplicationTest;
import com.demo.ldap.dao.UserRepository;
import com.demo.ldap.entity.User;
import com.demo.ldap.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.Context;

/**
 * @author ZhouFufeng
 * @since 2019/11/28
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class StaterTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getAllPersonNames() {
        userService.getAllPersonNames().forEach(System.out::println);
    }

    @Test
    public void getAllPersons() {
        userService.getAllPersons().forEach(System.out::println);
    }

    @Test
    public void getUserByLoginName() {
        String loginName = "ZFF";
        User user=userRepository.findByLoginName(loginName);
        System.out.println(user);

//        System.out.println(JSON.toJSONString(userService.getUserByLoginName("ZFF")));
    }


    @Test
    public void saveUser() {
        User user = new User();
        user.setLoginName("HHHH");
        user.setUserName("HHH");
        userRepository.save(user);
    }

    @Test
    public void getUser() throws Exception {
        String loginName = "ZFF";
        String password = "admin";
        User user = userService.getUser(loginName, password);
        System.out.println("id: "+user.getId());
        System.out.println("loginName: "+user.getLoginName());
        System.out.println("userName: "+user.getUserName());
        System.out.println("roles:");
        user.getRoles().forEach(System.out::println);
    }
}
