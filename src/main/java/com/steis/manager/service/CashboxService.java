package com.steis.manager.service;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashboxService {

    private final CashboxRepo cashboxRepo;
    private final MasterRepo masterRepo;

    @Autowired
    public CashboxService(CashboxRepo cashboxRepo, MasterRepo masterRepo) {
        this.cashboxRepo = cashboxRepo;
        this.masterRepo = masterRepo;
    }

    public List<Cashbox> findAll() {
        return cashboxRepo.findAll();
    }

    @Deprecated
    public Cashbox setMaster(Cashbox cashbox, Master master) {

        Long id = master.getId();
        // Костыль
        cashbox.setMaster(masterRepo.getOne(id));
        return cashboxRepo.save(cashbox);
    }
}
