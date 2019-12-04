package com.demo.ldap.dao;

import com.demo.ldap.entity.User;
import org.springframework.data.ldap.repository.LdapRepository;

/**
 * @author ZhouFufeng
 * @since 2019/11/28
 **/
public interface UserRepository extends LdapRepository<User> {
    User findByLoginName(String loginName);
}
