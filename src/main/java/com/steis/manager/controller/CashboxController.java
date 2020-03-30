package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    public String cashboxList(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "address", defaultValue = "") String address,
            @RequestParam(value = "master", required = false) Master master,
            @RequestParam(value = "isCase", required = false, defaultValue = "false") Boolean isCase,
            @PageableDefault(sort = {"nameModel"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        Page<Cashbox> cashboxes;
        if (!name.isEmpty() || !address.isEmpty()) {
            cashboxes = cashboxService.getCashboxesByFilter(name, address, isCase, pageable);
            cashboxes = filterByMaster(master, cashboxes);
        } else {
            cashboxes = cashboxService.findAll(pageable);
            cashboxes = filterByMaster(master, cashboxes);
        }

        model.addAttribute("cashboxes",cashboxes);
        model.addAttribute("url", "/cashboxes" + "?name=" + name + "&address=" + address + "&");
        return "cashboxList";
    }

    private Page<Cashbox> filterByMaster(Master master, Page<Cashbox> cashboxes) {
        if (master != null) {
            List<Cashbox> result = cashboxes
                    .toList()
                    .stream()
                    .filter(cashbox -> cashbox.getMaster() == master)
                    .collect(Collectors.toList());
            cashboxes = new PageImpl<>(result);
        }
        return cashboxes;
    }

    @GetMapping("setMaster")
    public String setMaster(
            @RequestParam("cashbox") Cashbox cashbox,
            @RequestParam("master") Master master,
            HttpServletRequest request) {

        cashboxService.setMaster(cashbox, master);

        return "redirect:" + request.getHeader("referer");

    }


    @ModelAttribute
    public void masterAttribute(Model model) {
        model.addAttribute("masters", masterRepo.findAllMaster());
    }
}
