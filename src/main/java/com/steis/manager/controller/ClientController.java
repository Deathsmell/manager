package com.steis.manager.controller;

import com.steis.manager.service.CashboxService;
import com.steis.manager.service.ClientService;
import com.steis.manager.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

@Controller
class ClientController {

    @Value("${upload.clientList.path}")
    private String path;

    private final ClientService clientService;
    private final CashboxService cashboxService;
    private final ExcelService excelService;

    @Autowired
    public ClientController(ClientService clientService, CashboxService cashboxService, ExcelService excelService) {
        this.clientService = clientService;
        this.cashboxService = cashboxService;
        this.excelService = excelService;
    }

    @GetMapping("/")
    public String main (Model model){
        File fileXls = new File(path);
        Boolean exist = fileXls.exists();
        model.addAttribute("fileExists", exist);
        return "main";
    }

    @GetMapping("/clients")
    public String getClients (@PageableDefault (sort = {"name"}, direction = Sort.Direction.ASC) Pageable page,
                              Model model){
        model.addAttribute("clients", clientService.getClients(page));
        model.addAttribute("url", "/index");
        return "clientList";
    }

    @GetMapping("/createExcel")
    public String createExcel (Model model){
        excelService.createExcel();
        return "redirect:/main";
    }

    @GetMapping("/readExcel")
    public String readExcel () throws IOException {
        excelService.readExcel();
        return "redirect:/main";
    }

}
