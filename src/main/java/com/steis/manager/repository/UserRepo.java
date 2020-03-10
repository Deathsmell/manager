package com.steis.manager.repository;

import com.steis.manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {


    User findUserByUsername(String username);
}
