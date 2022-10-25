package com.hotelsecure.service;

import com.hotelsecure.repo.RoleRepo;
import com.hotelsecure.repo.UserRepo;
import com.hotelsecure.tables.Role;
import com.hotelsecure.tables.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp  implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("user not found in the database");
            throw  new UsernameNotFoundException("user not found in the database");
        } else {
            log.info("user found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->  {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public User seveUser(User user) {
        log.info("saving a new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public Role seveRole(Role role) {
        log.info("saving a new role {} to the database", role.getName());
        return roleRepo.save(role);
    }
    @Override
    public void addRoleToUser(String username, String roleNmae) {
        log.info("adding roll {} to  user {}",roleNmae, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleNmae);
        user.getRoles().add(role);
    }
    @Override
    public User getUser(String username) {
        log.info("Fetching user {} ", username);
        return userRepo.findByUsername(username);
    }
    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
}
