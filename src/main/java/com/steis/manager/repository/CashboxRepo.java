package com.steis.manager.repository;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import com.steis.manager.domain.Master;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashboxRepo extends JpaRepository<Cashbox, Long> {

    @EntityGraph(attributePaths = {"client"})
    Page<Cashbox> findAllByOrderByAddress(Pageable pageable);

    @EntityGraph(attributePaths = {"client"})
    @Query("select cb from Cashbox cb where cb.master = :master and lower( cb.client.name) like %:name% and lower(cb.address) like %:address%")
    Page<Cashbox> findAllByMaster(@Param("master")Master master, @Param("name") String name, @Param("address") String address, Pageable pageable);

    List<Cashbox> findByClient(Client client);

    @EntityGraph(attributePaths = {"client"})
    @Query(value = "select cb from Cashbox cb where lower(cb.client.name) like %:name% and lower(cb.address) like %:address%")
    Page<Cashbox> findAllByClientNameAndAddress(@Param("name") String name, @Param("address") String address, Pageable pageable);

    @EntityGraph(attributePaths = {"client"})
    Page<Cashbox> findByMaster(Master master, Pageable pageable);
}
