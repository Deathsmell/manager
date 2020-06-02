package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cashboxes")
public class CashboxController {

    private final CashboxService cashboxService;

    private final MasterRepo masterRepo;


    @Autowired
    public CashboxController(CashboxService cashboxService, MasterRepo masterRepo) {
        this.cashboxService = cashboxService;
        this.masterRepo = masterRepo;
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


    @ModelAttribute
    public void masterAttribute(Model model, HttpServletRequest request) {
        model.addAttribute("masters", masterRepo.findAllMaster());
        model.addAttribute("whichMaster", request.getParameter("master"));
    }
}
