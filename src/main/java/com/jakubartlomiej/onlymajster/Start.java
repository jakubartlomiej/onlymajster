package com.jakubartlomiej.onlymajster;

import com.jakubartlomiej.onlymajster.user.model.Role;
import com.jakubartlomiej.onlymajster.user.repository.RoleRepository;
import com.jakubartlomiej.onlymajster.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Start {
    private final RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
        if (roleService.count() == 0) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleService.save(role);
        }
    }
}
