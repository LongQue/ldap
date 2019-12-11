package com.demo.ldap.service.impl;

import com.demo.ldap.dao.UserRepository;
import com.demo.ldap.entity.User;
import com.demo.ldap.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
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
    private LdapTemplate ldapTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getAllPersonNames() {
        return ((List<User>) userRepository.findAll()).stream().map(User::getUserName).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllPersons() {
        return ((List<User>) userRepository.findAll());
    }

    /**
     * 不通用，查询路径写死，查询路径为application base加上User@Entry(base)，且密码无法获取
     */
    @Override
    public User getUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }
    /**
     * 默认方法可实现通用查询
     * 1、先用ldapTemp获取登录名对应的全路径
     * 2、截取中间部分路径代替User@Entry(base)
     * 3、获取用户信息
     * 一样无法获得密码，但可以通过ldapTemp用登录名和密码验证是否存在
     */
    @Override
    public User getUser(String loginName,String password) throws Exception {
        // 组装查询条件
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("sAMAccountName", loginName));
        // 以登录名获取用户存储路径
        String fullBase=ldapTemplate.searchForContext(LdapQueryBuilder.query().filter(filter)).getNameInNamespace();
        // 获取中间段路径（该处查出的dc是小写）
        // 如  cn=ZFF,cn=user,dc=try,dc=com ——> cn=ZFF,cn=user
        fullBase = fullBase.replace(",dc=try,dc=com", "");
        //     cn=ZFF,cn=user ——> cn=users
        fullBase = fullBase.substring(fullBase.indexOf(',') + 1);
        // 认证登录名和密码,若账号不存在、密码不对或禁用返回false
        boolean authPass = ldapTemplate.authenticate(fullBase, filter.encode(), password);
        if (!authPass) {
           throw new Exception("账号不符合条件");
        }
        return userRepository.findOne(LdapQueryBuilder.query().base(fullBase).filter(filter)).get();
    }
}
