package com.hotelsecure.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Users {
    private Long id;
    private boolean IsLock;
    private String name;
    private String username;
    private String password;
    private String role;
    private Date expiry_date;
    private Collection<Role> roles = new ArrayList<>();

    public Users() {
    }

    public Users(Long id, boolean isLock, String name, String username, String password, String role, Date expiry_date, Collection<Role> roles) {
        this.id = id;
        IsLock = isLock;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.expiry_date = expiry_date;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isLock() {
        return IsLock;
    }

    public void setLock(boolean lock) {
        IsLock = lock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
