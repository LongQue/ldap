package com.demo.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@EnableLdapRepositories
@SpringBootApplication
public class AdLdapDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdLdapDemoApplication.class, args);
    }

}
