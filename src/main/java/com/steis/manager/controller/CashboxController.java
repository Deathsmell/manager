package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/cashboxes")
public class CashboxController {

    private final CashboxService cashboxService;

    private final MasterRepo masterRepo;

    @Autowired
    public CashboxController(CashboxService cashboxService, MasterRepo masterRepo) {
        this.cashboxService = cashboxService;
        this.masterRepo = masterRepo;
    }

    @GetMapping
    public String getCashboxes(Model model) {
        model.addAttribute("masters", masterRepo.findAllMaster());
        model.addAttribute("cashboxes", cashboxService.findAll());
        return "cashboxList";
    }


    @GetMapping("filtr")
    public String findCashboxesByFiltr(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam(value = "isCase", required = false, defaultValue = "false") Boolean isCase,
            Model model) {
        model.addAttribute("masters", masterRepo.findAllMaster());
        if (!isCase) {
            name = name.toUpperCase();
            address = address.toUpperCase();
        }
        if (!(address.isEmpty() || name.isEmpty())) {
            model.addAttribute("cashboxes", cashboxService.getCashboxesByUsernameAndAddress(name, address));
        } else if (address.isEmpty()) {
            model.addAttribute("cashboxes", cashboxService.getCashboxesByUsername(name));
        } else {
            model.addAttribute("cashboxes", cashboxService.getCashboxesByAddress(address));
        }
        return "cashboxList";
    }

    @PostMapping("setMaster")
    public String setMaster(
            @RequestParam("cashbox") Cashbox cashbox,
            @RequestParam("master") Master master) {
        cashboxService.setMaster(cashbox, master);
        return "redirect:cashboxList";
    }

}
