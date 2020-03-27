package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cashboxes")
public class CashboxController {

    private final CashboxService cashboxService;
    private final CashboxRepo cashboxRepo;

    private final MasterRepo masterRepo;

    @Autowired
    public CashboxController(CashboxService cashboxService, CashboxRepo cashboxRepo, MasterRepo masterRepo) {
        this.cashboxService = cashboxService;
        this.cashboxRepo = cashboxRepo;
        this.masterRepo = masterRepo;
    }


    @GetMapping
    public void showCashboxes(Iterable<Master> masters, Iterable<Cashbox> cashboxes, Model model) {
        Map<String, Iterable<?>> map = new HashMap<>();
        map.put("masters", masters);
        map.put("cashboxes", cashboxes);
        model.addAllAttributes(map);
    }

    @GetMapping("/all")
    public String showAll (Model model){
        showCashboxes(masterRepo.findAllMaster(),cashboxService.findAll() , model);
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

    @GetMapping("setMaster")
    public String setMaster(
            @RequestParam("cashbox") Cashbox cashbox,
            @RequestParam("master") Master master,
            HttpServletRequest request) {

        cashboxService.setMaster(cashbox,master);

        return "redirect:" + request.getHeader("referer");

    }

    @PostMapping("/list/master")
    public String findByMaster (@RequestParam Master master, Model model){
        model.addAttribute("master",masterRepo.findAllMaster());
        model.addAttribute("cashboxes", cashboxService.findByMaster(master));
        return "cashboxList";
    }

}
