package com.steis.manager.repository;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import com.steis.manager.domain.Master;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashboxRepo extends JpaRepository<Cashbox, Long> {

    @EntityGraph(attributePaths = { "client" })
    List<Cashbox> findAllByOrderByAddress();

    List<Cashbox> findByClient(Client client);

    @EntityGraph(attributePaths = { "client" })
    @Query(value = "select cb from Cashbox cb where upper(cb.address) like %:address% order by cb.address asc")
    List<Cashbox> findAllByAddressContaining(@Param("address") String address);

    @EntityGraph(attributePaths = { "client" })
    @Query(value = "select cb from Cashbox cb where upper(cb.client.name) like %:name% order by cb.address asc")
    List<Cashbox> findAllByClientName(@Param("name") String name);

    @EntityGraph(attributePaths = { "client" })
    @Query(value = "select cb from Cashbox cb where upper(cb.client.name) like %:name% and upper(cb.address) like %:address% order by cb.address asc")
    List<Cashbox> findAllByClientNameAndAddress(@Param("name") String name, @Param("address") String address);

    @EntityGraph(attributePaths = { "client" })
    List<Cashbox> findByMaster(Master master);
}
