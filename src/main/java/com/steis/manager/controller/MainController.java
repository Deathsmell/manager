package com.steis.manager.controller;

import com.steis.manager.service.ClientService;
import com.steis.manager.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {

    @Value("${upload.clientList.path}")
    private String path;

    private final ClientService clientService;
    private final ExcelService excelService;

    @Autowired
    public MainController(ClientService clientService, ExcelService excelService) {
        this.clientService = clientService;
        this.excelService = excelService;
    }

    @GetMapping("/main")
    public String main (Model model){
        File fileXls = new File(path);
        Boolean exist = fileXls.exists();
        model.addAttribute("fileExists", exist);
        return "main";
    }

    @GetMapping("/index")
    public String getClients (Model model){
        model.addAttribute("clients", clientService.getClients());
        return "index";
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