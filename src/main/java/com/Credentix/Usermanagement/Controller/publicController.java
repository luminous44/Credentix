package com.Credentix.Usermanagement.Controller;

import com.Credentix.Usermanagement.Entity.User;
import com.Credentix.Usermanagement.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ht")
public class publicController {
    @Autowired
    private  AdminService service;
    @GetMapping("/health")
    public String healthCK(){
        return "Ok";
    }

}
