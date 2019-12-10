package com.demo.ldap.dao;

import com.demo.ldap.entity.User;
import org.springframework.data.ldap.repository.LdapRepository;

/**
 * 与entity配合可自动映射字段
 * @author ZhouFufeng
 * @since 2019/11/28
 **/

public interface UserRepository extends LdapRepository<User> {
    User findByLoginName(String loginName);
}
