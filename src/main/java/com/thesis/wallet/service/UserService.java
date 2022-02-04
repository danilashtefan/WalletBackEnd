package com.thesis.wallet.service;

import com.thesis.wallet.entity.security.Role;
import com.thesis.wallet.entity.security.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
