package com.jakubartlomiej.onlymajster.user.repository;

import com.jakubartlomiej.onlymajster.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}