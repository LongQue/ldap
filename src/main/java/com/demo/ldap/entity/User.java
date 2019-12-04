package com.demo.ldap.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author ZFF
 * @Date 2019/11/13 16:51
 * @Version 1.0
 **/
@Data
@Entry(base = "cn=users",objectClasses = {"person","top","organizationalPerson","user"})
public final class User {
    @Id
    private Name id;

    @DnAttribute(value = "cn", index = 1)
    private String userName;

    @Attribute(name = "sAMAccountName")
    private String loginName;

    @Attribute(name = "memberOf")
    private List<String> role;

    @Attribute(name = "sn")
    private String surName;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "mobile")
    private String mobile;

    @Attribute(name = "telephoneNumber")
    private String phone;

    private String unitName;
}
