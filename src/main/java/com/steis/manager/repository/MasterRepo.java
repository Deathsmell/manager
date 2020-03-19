package com.steis.manager.repository;

import com.steis.manager.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MasterRepo extends JpaRepository<Master, Long> {
    Master findByName(String name);

    @Query("select m from Master m")
    List<Master> findAllMaster ();
}
