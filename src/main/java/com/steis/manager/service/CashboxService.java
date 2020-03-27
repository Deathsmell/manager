package com.steis.manager.service;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ArrayList<Cashbox> findAll(){
        List<Cashbox> cashboxes = cashboxRepo.findAllByOrderByAddress();
        return new ArrayList<>(cashboxes);
    }

    public Iterable<Cashbox> getCashboxesByUsername(String name) {
        return cashboxRepo.findAllByClientName(name);
    }

    public Iterable<Cashbox> getCashboxesByAddress (String address) {
        return cashboxRepo.findAllByAddressContaining(address);
    }

    public Iterable<Cashbox> getCashboxesByUsernameAndAddress (String name, String address){
        return cashboxRepo.findAllByClientNameAndAddress(name, address);
    }

    public void setMaster (Cashbox cashbox , Master master){
        cashbox.setMaster(master);
        cashboxRepo.save(cashbox);
    }

    public Iterable<Cashbox> findByMaster(Master master) {
        return cashboxRepo.findByMaster(master);
    }
}
