package com.demo.ldap.test;

import com.demo.ldap.ApplicationTest;
import com.demo.ldap.service.impl.UserServiceImpl;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class StaterTest {

    @Autowired
    private UserServiceImpl userService;

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
        System.out.println(userService.getUserByLoginName("ZFF"));
    }
}
