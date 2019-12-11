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

/**
 * base与application的base组成默认路径。
 * objectClasses默认查询条件
 */
@Entry(base = "cn=users",objectClasses = {"person", "top", "organizationalPerson", "user"})
public final class User {
    /**
     * 截取除dc外的路径
     */
    @Id
    private Name id;
    /**
     * 登录名，唯一，可作为查询条件 @Attribute设置字段映射
     */
    @Attribute(name = "sAMAccountName")
    private String loginName;
    /**
     * 所属权限组，类似于角色概念
     */
    @Attribute(name = "memberOf")
    private List<String> roles;
    /**
     * 用户昵称
     */
    @Attribute(name = "cn")
    private String userName;

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
