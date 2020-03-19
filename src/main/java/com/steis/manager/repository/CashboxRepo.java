package com.steis.manager.repository;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CashboxRepo extends JpaRepository<Cashbox, Long> {

    List<Cashbox> findByClient(Client client);

    @Query(value = "select cb from Client cl inner join Cashbox cb on cl.id=cb.client.id where upper(cb.address) like %?1%")
    List<Cashbox> findAllByAddressContaining(String address);

    @Query(value = "select cb from Client cl inner join Cashbox cb on cl.id=cb.client.id where upper(cl.name) like %?1%")
    List<Cashbox> findAllByClientName(@Param("name") String name);

    @Query(value = "select cb from Client cl inner join Cashbox cb on cl.id=cb.client.id where upper(cl.name) like %?1% and lower(cb.address) like %?2%")
    List<Cashbox> findAllByClientNameAndAddress(@Param("name") String name, @Param("address") String address);

}
