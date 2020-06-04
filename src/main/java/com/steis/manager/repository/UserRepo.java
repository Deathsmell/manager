package com.steis.manager.repository;

import com.steis.manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    User findUserByUsername(String username);
    Optional<User> findByUsername (String username);

}
