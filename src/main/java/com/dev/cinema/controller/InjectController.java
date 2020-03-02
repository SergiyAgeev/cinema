package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Resource(name = "inject")
public class InjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void postConstruct() {
        Role userRole = new Role();
        userRole.setRole("USER");
        Role adminRole = new Role();
        userRole.setRole("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);

        User user = new User();
        user.setEmail("user@user.com");
        user.setPassword(passwordEncoder.encode("123"));
        user.addRole(roleService.getRole("ADMIN"));
        userService.add(user);
    }

}
