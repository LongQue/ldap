package com.demo.ldap.service;

import com.demo.ldap.entity.User;

import java.util.List;

/**
 * @author ZhouFufeng
 * @since 2019/11/28
 **/
public interface IUserService {


    List<String> getAllPersonNames();

    List<User> getAllPersons();

    User getUserByLoginName(String loginName);

    User getUser(String loginName,String password) throws Exception;
}
