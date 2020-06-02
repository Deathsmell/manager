package com.steis.manager.controller;

import com.steis.manager.domain.Master;
import com.steis.manager.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/masters")
public class MasterController {

    private final MasterRepo masterRepo;

    @Autowired
    public MasterController(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    @GetMapping
    @CrossOrigin(origins = "${cros.access.path}")
    public List<Master> getAll (){
        return masterRepo.findAll();
    }
}
