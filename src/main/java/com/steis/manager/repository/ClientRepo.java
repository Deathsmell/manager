package com.steis.manager.repository;

import com.steis.manager.domain.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {

    List<Client> findAllByOrderByName();
}
