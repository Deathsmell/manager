package com.steis.manager.controller;

import com.steis.manager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    private final ClientService clientService;

    @Autowired
    public FileUploadController( ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile (@RequestParam("file") MultipartFile file, Model model){
        String massage = clientService.uploadFile(file);
        model.addAttribute("massage",massage);
        return "redirect:";
    }
}
