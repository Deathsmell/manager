package com.steis.manager.repository;

import com.steis.manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {

    User findUserByUsername(String username);

}
