package com.Credentix.Usermanagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class publicController {
    @GetMapping("/health")
    public String healthCK(){

        return "Ok";
    }
}
