package com.steis.manager.repository;

import com.steis.manager.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepo extends JpaRepository<Master, Long> {
    Master findByName(String name);
}
