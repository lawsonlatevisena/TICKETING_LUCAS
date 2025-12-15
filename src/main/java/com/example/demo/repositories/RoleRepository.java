package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Role;
import com.example.demo.utils.UserRoles;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Role r WHERE r.name = :name")
    Boolean existsRoleByName(UserRoles name);

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findByName(UserRoles name);
}
