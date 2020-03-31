package com.steis.manager.controller;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    // НЕ РАБОТАЕТ ПАГИНАЦИЯ ПРИ ВЫБОРКЕ ПО МАСТЕРАМ
    @GetMapping
    public String cashboxList(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "address", required = false, defaultValue = "") String address,
            @RequestParam(value = "master", required = false, defaultValue = "") Master master,
            @PageableDefault(sort = {"nameModel"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {

        Page<Cashbox> cashboxes;
        if (!name.isEmpty() || !address.isEmpty()) {
            cashboxes = cashboxService.getCashboxesByFilter(name, address, pageable);
        } else {
            cashboxes = cashboxService.findAll(pageable);
        }
        cashboxes = filterByMaster(master, cashboxes, pageable);

        String masterId = "";
        if (master!=null){
            masterId = master.getId().toString();
        }
        String url = "/cashboxes" + "?name=" + name + "&address=" + address + "&master=" + masterId + "&";

        model.addAttribute("cashboxes",cashboxes);
        model.addAttribute("url", url);
        return "cashboxList";
    }

    private Page<Cashbox> filterByMaster(Master master, Page<Cashbox> cashboxes, Pageable pageable) {
        if (master != null) {
            List<Cashbox> result = cashboxes
                    .toList()
                    .stream()
                    .filter(cashbox -> cashbox.getMaster() == master)
                    .collect(Collectors.toList());
            cashboxes = new PageImpl<>(result,pageable,result.size());
        }
        return cashboxes;
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
