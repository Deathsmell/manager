package com.steis.manager.repository;

import com.steis.manager.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client findByName(String name);


}
