package com.hotelsecure.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private boolean IsLock;
    private Date expiry_date;
    private String name;
    private String username;
    private String password;
    private String Role;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

//    public <E> User11(Object o, String zeev_resner, String zeev, String s, ArrayList<E> es) {
//    }
}