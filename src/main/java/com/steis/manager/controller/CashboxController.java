package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public String cashboxList(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "address", required = false, defaultValue = "") String address,
            @RequestParam(value = "master", required = false, defaultValue = "") Master master,
            @PageableDefault(sort = {"nameModel"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {

        Page<Cashbox> cashboxes;
        String masterId = "";

        if (master != null) {
            masterId = master.getId().toString();
            cashboxes = cashboxService.findByMaster(master, name, address , pageable);
        } else if (!name.isEmpty() || !address.isEmpty()) {
            cashboxes = cashboxService.getCashboxesByFilter(name, address, pageable);
        } else {
            cashboxes = cashboxService.findAll(pageable);
        }

        String url = "/cashboxes" + "?name=" + name + "&address=" + address + "&master=" + masterId + "&";

        model.addAttribute("cashboxes", cashboxes);
        model.addAttribute("url", url);
        return "cashboxList";
    }

    @PostMapping("setMaster")
    public String setMaster(
            @RequestParam("cashbox") Cashbox cashbox,
            @RequestParam("master") Master master,
            HttpServletRequest request) {

        cashboxService.setMaster(cashbox, master);

        return "redirect:" + request.getHeader("referer");
    }


    @ModelAttribute
    public void masterAttribute(Model model, HttpServletRequest request) {
        model.addAttribute("masters", masterRepo.findAllMaster());
        model.addAttribute("whichMaster", request.getParameter("master"));
    }
}
