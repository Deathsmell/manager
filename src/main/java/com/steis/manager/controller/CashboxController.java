package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashboxes")
public class CashboxController {

    private final CashboxService cashboxService;

    @Autowired
    public CashboxController(CashboxService cashboxService) {
        this.cashboxService = cashboxService;
    }

    @GetMapping("/getAll")
    @CrossOrigin(origins = "${cros.access.path}")
    public List<Cashbox> getAll() {
        return cashboxService.findAll();
    }


    @Deprecated
    @PostMapping(value = "setMaster/{id}")
    @CrossOrigin(origins = "${cros.access.path}")
    public Cashbox setMaster(
            @PathVariable("id") Cashbox cashbox,
            @RequestBody Master master
    ) {

        return cashboxService.setMaster(cashbox, master);

    }
}
