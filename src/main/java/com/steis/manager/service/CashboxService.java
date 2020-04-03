package com.steis.manager.service;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CashboxService {

    private final CashboxRepo cashboxRepo;
    private final MasterRepo masterRepo;

    @Autowired
    public CashboxService(CashboxRepo cashboxRepo, MasterRepo masterRepo) {
        this.cashboxRepo = cashboxRepo;
        this.masterRepo = masterRepo;
    }

    public Page<Cashbox> findAll(Pageable pageable) {
        return cashboxRepo.findAllByOrderByAddress(pageable);

    }

    public void setMaster(Cashbox cashbox, Master master) {
        cashbox.setMaster(master);
        cashboxRepo.save(cashbox);
    }

    public Page<Cashbox> findByMaster(Master master, Pageable pageble) {
        return cashboxRepo.findByMaster(master, pageble);
    }

    public Page<Cashbox> findByMaster(Master master, String name, String address, Pageable pageable){
        name = name.toLowerCase();
        address = address.toLowerCase();
        return cashboxRepo.findAllByMaster(master, name, address, pageable);
    }

    public Page<Cashbox> getCashboxesByFilter(String name, String address, Pageable pageable) {
            name = name.toLowerCase();
            address = address.toLowerCase();

        if (address.isEmpty() || !name.isEmpty()) {
            return cashboxRepo.findAllByClientNameAndAddress(name, address, pageable);
        } else return null;
    }
}
