package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.utils.UserRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private UserRoles name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public UserRoles getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(UserRoles name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
