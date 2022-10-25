package com.hotelsecure.service;

import com.hotelsecure.tables.Role;
import com.hotelsecure.tables.User;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin
public interface UserService {
    User seveUser(User user);
    Role seveRole(Role role);
    void addRoleToUser(String username, String roleNmae);
    User getUser(String username);
    List<User> getUsers();
}
