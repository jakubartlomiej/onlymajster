package com.jakubartlomiej.onlymajster.user.repository;

import com.jakubartlomiej.onlymajster.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByEmail(String email);
}