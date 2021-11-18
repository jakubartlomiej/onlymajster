package com.jakubartlomiej.onlymajster.user.service;

import com.jakubartlomiej.onlymajster.user.model.Role;
import com.jakubartlomiej.onlymajster.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public long count() {
        return roleRepository.count();
    }
}