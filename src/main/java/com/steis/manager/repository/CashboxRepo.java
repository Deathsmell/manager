package com.steis.manager.repository;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashboxRepo extends JpaRepository<Cashbox, Long> {

    List<Cashbox> findByClient (Client client);
}
