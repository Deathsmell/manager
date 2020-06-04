package com.steis.manager.controller;

import com.steis.manager.domain.Master;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.CashboxService;
import com.steis.manager.service.ClientService;
import com.steis.manager.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
class ClientController {

//    @Value("${upload.clientList.path}")
//    private String path;

    private final ClientService clientService;
    private final CashboxService cashboxService;
    private final ExcelService excelService;
    private final MasterRepo masterRepo;

    @Autowired
    public ClientController(ClientService clientService,
                            CashboxService cashboxService,
                            ExcelService excelService,
                            MasterRepo masterRepo) {
        this.clientService = clientService;
        this.cashboxService = cashboxService;
        this.excelService = excelService;
        this.masterRepo = masterRepo;
    }

//    @GetMapping("/")
//    public String main (Model model){
//        File fileXls = new File(path);
//        Boolean exist = fileXls.exists();
//        model.addAttribute("fileExists", exist);
//        return "main";
//    }

    //    @GetMapping("/createExcel")
//    public String createExcel (Model model){
//        excelService.createExcel();
//        return "redirect:/main";
//    }
//
    @GetMapping("/readExcel")
    public boolean readExcel() throws IOException {
        excelService.readExcel();
        return true;
    }

    @GetMapping("/createMasters")
    public List<Master> createMasters() {
        Master alex = new Master();
        Master oleg = new Master();
        Master petrovich = new Master();

        alex.setName("Александр");
        oleg.setName("Олег");
        petrovich.setName("Петрович");

        return masterRepo.saveAll(List.of(alex, oleg, petrovich));
    }

}
